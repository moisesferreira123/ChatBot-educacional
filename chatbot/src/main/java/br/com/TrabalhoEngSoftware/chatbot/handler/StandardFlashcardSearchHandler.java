package br.com.TrabalhoEngSoftware.chatbot.handler;

import org.springframework.stereotype.Component;

import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeSearchHandler;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class StandardFlashcardSearchHandler implements FlashcardTypeSearchHandler {
  
  @Override
  public String supportsType() {
    return "STANDARD_FLASHCARD";
  }

  @Override
  public Predicate getWordSearchPredicate(Root<FlashcardEntity> root, CriteriaBuilder criteriaBuilder, String word) {
    Predicate wordPredicate = criteriaBuilder.conjunction();
    Root<StandardFlashcardEntity> standardFlashcardRoot = criteriaBuilder.treat(root, StandardFlashcardEntity.class);
    if(word != null && !word.isEmpty()) {
			wordPredicate = criteriaBuilder.or(
        criteriaBuilder.like(criteriaBuilder.lower(standardFlashcardRoot.get("front")), "%" + word.toLowerCase() + "%"),
        criteriaBuilder.like(criteriaBuilder.lower(standardFlashcardRoot.get("back")), "%" + word.toLowerCase() + "%")
      );
		}
    return criteriaBuilder.and(wordPredicate);
  }
}
