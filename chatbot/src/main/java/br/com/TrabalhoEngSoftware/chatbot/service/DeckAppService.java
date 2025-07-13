package br.com.TrabalhoEngSoftware.chatbot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
import br.com.TrabalhoEngSoftware.chatbot.specification.DeckSpecification;
import br.com.TrabalhoEngSoftwareFramework.framework.dto.DeckSummaryDTO;
import br.com.TrabalhoEngSoftwareFramework.framework.entity.DeckEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.exception.ObjectNotFoundException;
import br.com.TrabalhoEngSoftwareFramework.framework.exception.UnauthorizedObjectAccessException;
import br.com.TrabalhoEngSoftwareFramework.framework.repository.DeckRepository;
import br.com.TrabalhoEngSoftwareFramework.framework.service.DeckService;

@Service
public class DeckAppService extends DeckService {

  public DeckAppService(DeckRepository deckRepository) {
    super(deckRepository);
  }

  @Override
  public Page<DeckSummaryDTO> listDecks(String title, String topic, Long userId, String sortType, Pageable pageable) {
    DeckSpecification builder = new DeckSpecification(title, topic);
    builder.addDesiredSpecification("filterByTitle");
    builder.addDesiredSpecification("filterByTopic");

    if(sortType != null && !sortType.trim().isEmpty()) {
      builder.addDesiredSpecification(sortType); // Adiciona a ordenação nomeada
    }

    Specification<DeckEntity> specification = builder.build(userId, "userEntity");
    return deckRepository.findAll(specification, pageable).map(DeckSummaryDTO::new);
  }

  public long getDueFlashcardsTotal(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
			throw new UnauthorizedObjectAccessException("Unauthorized to see due flashcards total this deck");
		}
    LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
    long dueFlashcardsTotal = deck.getFlashcards().stream()
                                  .filter(flashcard -> {
                                    if(flashcard.getFlashcardType().equals("STANDARD_FLASHCARD")) {
                                      StandardFlashcardEntity standardFlashcard = (StandardFlashcardEntity) flashcard;
                                      return standardFlashcard.getNextReview().isBefore(tomorrow);
                                    }
                                    return false;
                                  })
                                  .count();
    return dueFlashcardsTotal;
  }

  public double getMasteryLevel(Long deckId, Long userId) {
    DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
    if(!deck.getUserEntity().getId().equals(userId)) {
			throw new UnauthorizedObjectAccessException("Unauthorized to see mastery level this deck");
		}

    int repetitionMastery = 4;
    long dominatedFlashcards = deck.getFlashcards().stream()
                            .filter(flashcard -> {
                              if(flashcard.getFlashcardType().equals("STANDARD_FLASHCARD")) {
                                StandardFlashcardEntity standardFlashcard = (StandardFlashcardEntity) flashcard;
                                return standardFlashcard.getRepetition() >= repetitionMastery;
                              }
                              return false;
                            })
                            .count();

    int flashcardsTotal = deck.getFlashcards().size();
    if (flashcardsTotal == 0) return 0.0; 

    double masteryLevel = (double) dominatedFlashcards/flashcardsTotal;
    return masteryLevel;
  } 
}
