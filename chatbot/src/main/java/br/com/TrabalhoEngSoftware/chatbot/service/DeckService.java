package br.com.TrabalhoEngSoftware.chatbot.service;

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
    if(deckDTO.getTitle().trim().isEmpty()) {
      throw new IllegalArgumentException("Deck title can't be empty");
    }
    deck.setTitle(deckDTO.getTitle());
    deck.setTopic(deckDTO.getTopic());
    deck.setUserEntity(new UserEntity(userId));
    deckRepository.save(deck);
  }

  public Page<DeckSummaryDTO> listDecks(String title, String topic, Long userId, String sortType, Pageable pageable) {
    DeckSpecificationBuilder builder = new DeckSpecificationBuilder().filterByTitle(title).filterByTopic(topic);

    // TODO: Colocar as ordenações do nível de domínio.
    if ("createdAtAsc".equalsIgnoreCase(sortType)) {
      builder.sortByCreatedAtAsc();
    } else if ("createdAtDesc".equalsIgnoreCase(sortType)) {
      builder.sortByCreatedAtDesc();
    } else if ("lastReviewedAtAsc".equalsIgnoreCase(sortType)) {
      builder.sortByLastReviewedAtAsc();
    } else if ("lastReviewedAtDesc".equalsIgnoreCase(sortType)) {
      builder.sortByLastReviewedAtDesc();
    } else if ("totalFlashcardsDesc".equalsIgnoreCase(sortType)) {
      builder.sortByTotalFlashcardsDesc();
    } else if("totalDueFlashcardsAsc".equalsIgnoreCase(sortType)) {
      builder.sortByTotalDueFlashcardsAsc();
    } else if("totalDueFlashcardsDesc".equalsIgnoreCase(sortType)) {
      builder.sortByTotalDueFlashcardsDesc();
    }

    Specification<DeckEntity> specification = builder.build(userId);
    return deckRepository.findAll(specification, pageable).map(DeckSummaryDTO::new);
  }

}
