package br.com.TrabalhoEngSoftware.chatbot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.TrabalhoEngSoftware.chatbot.dto.FlashcardDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.FlashcardSummaryDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;
import br.com.TrabalhoEngSoftware.chatbot.repository.DeckRepository;
import br.com.TrabalhoEngSoftware.chatbot.repository.FlashcardRepository;
import br.com.TrabalhoEngSoftware.chatbot.specification.FlashcardSpecificationBuilder;

@Service
public class FlashcardService {

  final int WRONG = 0;
  final int HARD = 2;
  final int GOOD = 4;
  final int EASY = 5;

  final double MIN_EASE_FACTOR = 1.3;
  
  @Autowired
  private FlashcardRepository flashcardRepository;
  @Autowired
  private DeckRepository deckRepository;

  public FlashcardService(FlashcardRepository flashcardRepository, DeckRepository deckRepository) {
    this.flashcardRepository = flashcardRepository;
    this.deckRepository = deckRepository;
  }

  @Transactional
  public void createFlashcard(FlashcardDTO flashcardDTO, Long deckId) {
    FlashcardEntity flashcard = new FlashcardEntity();
    if(flashcardDTO.getFront() == null || flashcardDTO.getFront().trim().isEmpty()) {
      throw new IllegalArgumentException("Flashcard front can't be empty");
    }
    
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Flashcard deck not found"));
    flashcard.setFront(flashcardDTO.getFront());
    flashcard.setBack(flashcardDTO.getBack());
    flashcard.setNextReview(LocalDateTime.now());
    flashcard.setRepetition(0);
    flashcard.setEaseFactor(2.5);
    flashcard.setInterval(1);
    flashcard.setDeckEntity(deck);
    
    flashcard.getDeckEntity().getFlashcards().add(flashcard);
    //flashcardRepository.save(flashcard);
  }

  public Page<FlashcardSummaryDTO> listFlashcards(String word, boolean dominatedFlashcard, boolean undominatedFlashcard, Long userId, Long deckId, String sortType, Pageable pageable) {
    FlashcardSpecificationBuilder builder = new FlashcardSpecificationBuilder().filterByWord(word);

    if(dominatedFlashcard) builder.filterByDominatedFlashcards();
    if(undominatedFlashcard) builder.filterByUndominatedFlashcards();

    if ("createdAtAsc".equalsIgnoreCase(sortType)) {
      builder.sortByCreatedAtAsc();
    } else if ("createdAtDesc".equalsIgnoreCase(sortType)) {
      builder.sortByCreatedAtDesc();
    } else if ("lastReviewedAtAsc".equalsIgnoreCase(sortType)) {
      builder.sortByLastReviewedAtAsc();
    } else if ("lastReviewedAtDesc".equalsIgnoreCase(sortType)) {
      builder.sortByLastReviewedAtDesc();
    } else if ("nextReviewAsc".equalsIgnoreCase(sortType)) {
      builder.sortByNextReviewAsc();
    } else if("nextReviewDesc".equalsIgnoreCase(sortType)) {
      builder.sortByNextReviewDesc();
    } 

    Specification<FlashcardEntity> specification = builder.build(userId, deckId);
    return flashcardRepository.findAll(specification, pageable).map(FlashcardSummaryDTO::new);
  }

  @Transactional
  public void updateFlashcard(Long flashcardId, FlashcardSummaryDTO flashcardSummaryDTO, Long userId) {
    FlashcardEntity flashcard = flashcardRepository.findById(flashcardId).orElseThrow(() -> new RuntimeException("Flashcard not found"));

    if(!flashcard.getDeckEntity().getUserEntity().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to edit this flashcard");
    }

    if(flashcardSummaryDTO.getFront() == null || flashcardSummaryDTO.getFront().trim().isEmpty()) {
      throw new IllegalArgumentException("Front flashcard can't be empty");
    }
    flashcard.setFront(flashcardSummaryDTO.getFront());

    if(flashcardSummaryDTO.getBack() != null) {
      flashcard.setBack(flashcardSummaryDTO.getBack());
    }
  }

  @Transactional
  public void deleteFlashcard(Long flashcardId, Long userId) {
    FlashcardEntity flashcard = flashcardRepository.findById(flashcardId).orElseThrow(() -> new RuntimeException("Flashcard not found"));
    
    if(!flashcard.getDeckEntity().getUserEntity().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to edit this flashcard");
    }
    
    flashcard.getDeckEntity().getFlashcards().remove(flashcard);
    // flashcardRepository.delete(flashcard);
  }

  @Transactional
  public FlashcardSummaryDTO getFlashcardById(Long flashcardId, Long userId) {
    FlashcardEntity flashcard = flashcardRepository.findById(flashcardId).orElseThrow(() -> new RuntimeException("Flashcard not found"));
    
    if(!flashcard.getDeckEntity().getUserEntity().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to get this flashcard");
    }

    return new FlashcardSummaryDTO(flashcard);
  }

  public Optional<FlashcardSummaryDTO> getNextDueFlashcardByDeckId(Long deckId, Long userId) {
    LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
    Page<FlashcardEntity> page = flashcardRepository.findNextDueFlashcard(deckId, userId, tomorrow, PageRequest.of(0, 1));

    return page.stream().findFirst().map(FlashcardSummaryDTO::new);
  }

  @Transactional
  public void applyReviewResult(Long flashcardId, int answer, Long userId) {
    FlashcardEntity flashcard = flashcardRepository.findById(flashcardId).orElseThrow(() -> new RuntimeException("Flashcard not found"));

    if(!flashcard.getDeckEntity().getUserEntity().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to review this flashcard");
    }

    LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();

    double easeFactor = flashcard.getEaseFactor();

    if(answer != WRONG && answer != HARD && answer != GOOD && answer != EASY) {
      throw new RuntimeException("Unexpected response");
    }

    if(answer == WRONG) {
      flashcard.setRepetition(0);
      if(LocalDateTime.now().plusMinutes(1L).isBefore(tomorrow)){
        flashcard.setNextReview(LocalDateTime.now().plusMinutes(1L));
      } else {
        flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
      }
      flashcard.setInterval(1);
      flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
    } else {
      flashcard.setRepetition(flashcard.getRepetition()+1);
      if(flashcard.getRepetition() == 1){
        if(answer == HARD) {
          if(LocalDateTime.now().plusMinutes(5L).isBefore(tomorrow)){
            flashcard.setNextReview(LocalDateTime.now().plusMinutes(5L));
          } else {
            flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
          }
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
        }
        if(answer == GOOD) { 
          if(LocalDateTime.now().plusMinutes(10L).isBefore(tomorrow)){
            flashcard.setNextReview(LocalDateTime.now().plusMinutes(10L));
          } else {
            flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
          }
        }
        if(answer == EASY) {
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
          flashcard.setInterval((int) Math.ceil(flashcard.getInterval()*flashcard.getEaseFactor()));
          flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
        }
      } else {
        if(flashcard.getInterval() == 1) {
          flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
        } else {
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
          flashcard.setInterval((int) Math.ceil(flashcard.getInterval()*flashcard.getEaseFactor()));
          flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
        }
      }
    }

    flashcard.setLastReviewedAt(LocalDateTime.now());
    flashcard.getDeckEntity().setLastReviewedAt(LocalDateTime.now());
    flashcardRepository.save(flashcard);
  }

  protected double calculateEaseFactor(double easeFactor, int answer) {
    double easeFactorTemp = easeFactor - 0.8 + (0.28*answer) - (0.02*Math.pow(answer,2));
    return Math.max(MIN_EASE_FACTOR, easeFactorTemp);
  }

  @Transactional
  public long getCountNewFlashcards(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to count the learning flashcards this deck.");
    }
    return flashcardRepository.countNewFLashcards(deckId, userId);
  }

  @Transactional
  public long getCountLearningFlashcards(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to count the learning flashcards this deck.");
    }
    LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
    LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
    return flashcardRepository.countLearningFlashcards(deckId, userId, startOfToday, endOfToday);
  }

  @Transactional
  public long getCountReviewFlashcards(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to count the learning flashcards this deck.");
    }
    LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
    LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
    return flashcardRepository.countReviewFlashcards(deckId, userId, startOfToday, endOfToday);
  }
}
