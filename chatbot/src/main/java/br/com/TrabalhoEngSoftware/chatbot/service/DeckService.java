package br.com.TrabalhoEngSoftware.chatbot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.TrabalhoEngSoftware.chatbot.dto.DeckDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.DeckSummaryDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.repository.DeckRepository;
import br.com.TrabalhoEngSoftware.chatbot.specification.DeckSpecificationBuilder;

@Service
public class DeckService {
  
  @Autowired
  private DeckRepository deckRepository;

  public DeckService (DeckRepository deckRepository) {
    this.deckRepository = deckRepository;
  }

  @Transactional
  public void createDeck(DeckDTO deckDTO, Long userId) {
    DeckEntity deck = new DeckEntity();
    if(deckDTO.getTitle() == null || deckDTO.getTitle().trim().isEmpty()) {
      throw new IllegalArgumentException("Deck title can't be empty");
    }
    deck.setTitle(deckDTO.getTitle());
    deck.setTopic(deckDTO.getTopic());
    deck.setUserEntity(new UserEntity(userId));
    deckRepository.save(deck);
  }

  public Page<DeckSummaryDTO> listDecks(String title, String topic, Long userId, String sortType, Pageable pageable) {
    DeckSpecificationBuilder builder = new DeckSpecificationBuilder().filterByTitle(title).filterByTopic(topic);

    if ("createdAtAsc".equalsIgnoreCase(sortType)) {
      builder.sortByCreatedAtAsc();
    } else if ("createdAtDesc".equalsIgnoreCase(sortType)) {
      builder.sortByCreatedAtDesc();
    } else if ("lastReviewedAtAsc".equalsIgnoreCase(sortType)) {
      builder.sortByLastReviewedAtAsc();
    } else if ("lastReviewedAtDesc".equalsIgnoreCase(sortType)) {
      builder.sortByLastReviewedAtDesc();
    } else if ("totalFlashcardsDesc".equalsIgnoreCase(sortType)) {
      builder.sortByFlashcardsTotalDesc();
    } else if("totalDueFlashcardsAsc".equalsIgnoreCase(sortType)) {
      builder.sortByDueFlashcardsTotalAsc();
    } else if("totalDueFlashcardsDesc".equalsIgnoreCase(sortType)) {
      builder.sortByDueFlashcardsTotalDesc();
    } else if("masteryLevelAsc".equalsIgnoreCase(sortType)) {
      builder.sortByMasteryLevelAsc();
    } else if("masteryLevelDesc".equalsIgnoreCase(sortType)) {
      builder.sortByMasteryLevelDesc();
    }

    Specification<DeckEntity> specification = builder.build(userId);
    return deckRepository.findAll(specification, pageable).map(DeckSummaryDTO::new);
  }

  @Transactional 
  public DeckSummaryDTO updateDeck(Long deckId, DeckSummaryDTO deckSummaryDTO, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
    System.out.println("oi");
    if(!deck.getUserEntity().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to edit this deck");
    }
    System.out.println("oi");
    if(deckSummaryDTO.getTitle() != null) {
      if(deckSummaryDTO.getTitle().trim().isEmpty()) {
				throw new IllegalArgumentException("Title deck can't be empty");
			}
	    deck.setTitle(deckSummaryDTO.getTitle());
    }
    System.out.println("oi");
    if(deckSummaryDTO.getTopic() != null){
      deck.setTopic(deckSummaryDTO.getTopic());
    }

    return new DeckSummaryDTO(deckRepository.save(deck));
  }

  @Transactional
  public void deleteDeck(Long deckId, Long userId) {
    // TODO: Trocar por Exceptions criados por nós
		DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
		if(!deck.getUserEntity().getId().equals(userId)) {
			// TODO: Trocar por Exceptions criados por nós
			throw new RuntimeException("Unauthorized to delete this deck");
		}
		deckRepository.delete(deck);
  }

  public long getDueFlashcardsTotal(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
			// TODO: Trocar por Exceptions criados por nós
			throw new RuntimeException("Unauthorized to see due flashcards total this deck");
		}
    LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
    long dueFlashcardsTotal = deck.getFlashcards().stream()
                                  .filter(flashcard -> flashcard.getNextReview().isBefore(tomorrow))
                                  .count();
    return dueFlashcardsTotal;
  }

  public double getMasteryLevel(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
			// TODO: Trocar por Exceptions criados por nós
			throw new RuntimeException("Unauthorized to see mastery level this deck");
		}

    int repetitionMastery = 3;
    long dominatedFlashcards = deck.getFlashcards().stream()
                            .filter(flashcard -> flashcard.getRepetition() > repetitionMastery)
                            .count();

    int totalFlashcards = deck.getFlashcards().size();
    if (totalFlashcards == 0) return 0.0; 

    double masteryLevel = (double) dominatedFlashcards/totalFlashcards;
    return masteryLevel;
  } 
}
