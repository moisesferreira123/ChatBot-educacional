package br.com.TrabalhoEngSoftware.chatbot.handler;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeSearchHandler;
import jakarta.persistence.criteria.Predicate;

@Component
public class StandardFlashcardSearchHandler implements FlashcardTypeSearchHandler<StandardFlashcardEntity> {
  
  @Override
  public String supportsType() {
    return "STANDARD_FLASHCARD";
  }

  @Override
  public Specification<StandardFlashcardEntity> getWordSearchSpecification(String word) {
    return (root, query, criteriaBuilder) -> {
      Predicate wordPredicate = criteriaBuilder.conjunction();
      if(word != null && !word.isEmpty()) {
		  	wordPredicate = criteriaBuilder.or(
          criteriaBuilder.like(criteriaBuilder.lower(root.get("front")), "%" + word.toLowerCase() + "%"),
          criteriaBuilder.like(criteriaBuilder.lower(root.get("back")), "%" + word.toLowerCase() + "%")
        );
		  }
      return criteriaBuilder.and(wordPredicate);
    };
  }
}
