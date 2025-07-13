package br.com.TrabalhoEngSoftware.chatbot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.TrabalhoEngSoftware.chatbot.config.Constants;
import br.com.TrabalhoEngSoftware.chatbot.repository.FlashcardAppRepository;
import br.com.TrabalhoEngSoftware.chatbot.specification.FlashcardSpecification;
import br.com.TrabalhoEngSoftwareFramework.framework.dto.FlashcardDTO;
import br.com.TrabalhoEngSoftwareFramework.framework.dto.UserAnswerDTO;
import br.com.TrabalhoEngSoftwareFramework.framework.entity.DeckEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.exception.ObjectNotFoundException;
import br.com.TrabalhoEngSoftwareFramework.framework.exception.UnauthorizedObjectAccessException;
import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeHandler;
import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeSearchRegistry;
import br.com.TrabalhoEngSoftwareFramework.framework.service.FlashcardService;

@Service
public class FlashcardAppService extends FlashcardService {

  @Autowired
  private FlashcardAppRepository flashcardAppRepository;

  @Autowired 
  private FlashcardTypeSearchRegistry flashcardTypeSearchRegistry;

  public FlashcardAppService() {
    super();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Page<FlashcardDTO> listFlashcards(String word, String flashcardFilter, Long userId, Long deckId, String sortType, Pageable pageable) {
    FlashcardSpecification flashcardSpecification = new FlashcardSpecification(flashcardTypeSearchRegistry);
    flashcardSpecification.addWordFilter(word);

    if(flashcardFilter != null && !flashcardFilter.trim().isEmpty()) {
      flashcardSpecification.addDesiredSpecification(flashcardFilter); // Adiciona o filtro nomeado
    }

    if(sortType != null && !sortType.trim().isEmpty()) {
      flashcardSpecification.addDesiredSpecification(sortType); // Adiciona a ordenação nomeada
    }

    Specification<FlashcardEntity> specification = flashcardSpecification.build(deckId, "deckEntity");
    
    return flashcardRepository.findAll(specification, pageable).map(flashcardEntity -> {
      FlashcardTypeHandler<FlashcardDTO, FlashcardEntity, UserAnswerDTO> handler = (FlashcardTypeHandler<FlashcardDTO, FlashcardEntity, UserAnswerDTO>) handlerRegistry.getHandler(flashcardEntity.getFlashcardType());
      return handler.entityToDTO(flashcardEntity);
    });
  }

  @SuppressWarnings("unchecked")
  public Optional<FlashcardDTO> getNextDueFlashcardByDeckId(Long deckId, Long userId) {
    LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
    Page<FlashcardEntity> page = flashcardAppRepository.findNextDueFlashcardByDeckId(deckId, userId, tomorrow, PageRequest.of(0, 1));
    return page.stream().findFirst().map(flashcardEntity -> {
      FlashcardTypeHandler<FlashcardDTO, FlashcardEntity, UserAnswerDTO> handler = (FlashcardTypeHandler<FlashcardDTO, FlashcardEntity, UserAnswerDTO>) handlerRegistry.getHandler(flashcardEntity.getFlashcardType());
      return handler.entityToDTO(flashcardEntity);
    });
  }

  protected double calculateEaseFactor(double easeFactor, int answer) {
    double easeFactorTemp = easeFactor - 0.8 + (0.28*answer) - (0.02*Math.pow(answer,2));
    return Math.max(Constants.MIN_EASE_FACTOR, easeFactorTemp);
  }

  @Transactional
  public long getCountNewFlashcards(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
      throw new UnauthorizedObjectAccessException("Unauthorized to count the learning flashcards this deck.");
    }
    return flashcardAppRepository.countNewFlashcards(deckId, userId);
  }

  @Transactional
  public long getCountLearningFlashcards(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
      throw new UnauthorizedObjectAccessException("Unauthorized to count the learning flashcards this deck.");
    }
    LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
    LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
    return flashcardAppRepository.countLearningFlashcards(deckId, userId, startOfToday, endOfToday);
  }

  @Transactional
  public long getCountReviewFlashcards(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
      throw new UnauthorizedObjectAccessException("Unauthorized to count the learning flashcards this deck.");
    }
    LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
    LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
    return flashcardAppRepository.countReviewFlashcards(deckId, userId, startOfToday, endOfToday);
  }

  @SuppressWarnings("unchecked")
  public Optional<FlashcardDTO> getNextDueFlashcard(Long userId) {
    LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
    Page<FlashcardEntity> page = flashcardAppRepository.findNextDueFlashcard(userId, tomorrow, PageRequest.of(0, 1));

    return page.stream().findFirst().map(flashcardEntity -> {
      FlashcardTypeHandler<FlashcardDTO, FlashcardEntity, UserAnswerDTO> handler = ( FlashcardTypeHandler<FlashcardDTO, FlashcardEntity, UserAnswerDTO>) handlerRegistry.getHandler(flashcardEntity.getFlashcardType());
      return handler.entityToDTO(flashcardEntity);
    });
  }

  @Transactional
  public long getCountAllNewFlashcards(Long userId) {
    return flashcardAppRepository.countAllNewFlashcards(userId);
  }

  @Transactional
  public long getCountAllLearningFlashcards(Long userId) {
    LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
    LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
    return flashcardAppRepository.countAllLearningFlashcards(userId, startOfToday, endOfToday);
  }

  @Transactional
  public long getCountAllReviewFlashcards(Long userId) {
    LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
    LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
    return flashcardAppRepository.countAllReviewFlashcards(userId, startOfToday, endOfToday);
  }
}
