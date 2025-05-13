package br.com.TrabalhoEngSoftware.chatbot.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;
import jakarta.persistence.criteria.Predicate;

public class FlashcardSpecification {
  // Filtro por palavra em relação ao front e ao back
  public static Specification<FlashcardEntity> filterByWord(String word) {
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

  public static Specification<FlashcardEntity> filterByDominatedFlashcards() {
    return (root, query, criteriaBuilder) -> {
      int repetitionMastery = 4;
      return criteriaBuilder.greaterThanOrEqualTo(root.get("repetition"), repetitionMastery); 
    };
  }

  public static Specification<FlashcardEntity> filterByUndominatedFlashcards() {
    return (root, query, criteriaBuilder) -> {
      int repetitionMastery = 4;
      return criteriaBuilder.lessThan(root.get("repetition"), repetitionMastery); 
    };
  }

  // Ordenação por data de criação mais recente
	public static Specification<FlashcardEntity> sortByCreatedAtDesc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
			return criteriaBuilder.conjunction();
		};
	}
	
  // Ordenação por data de criação mais antiga
	public static Specification<FlashcardEntity> sortByCreatedAtAsc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.asc(root.get("createdAt")));
			return null;
		};
	}

  // Ordenação pela data de última revisão feita (mais recente)
	public static Specification<FlashcardEntity> sortByLastReviewedAtDesc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.desc(root.get("lastReviewedAt")));
			return null;
		};
	}

	// Ordenação pela data de última revisão feita (mais antiga)
	public static Specification<FlashcardEntity> sortByLastReviewedAtAsc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.asc(root.get("lastReviewedAt")));
			return null;
		};
	}

  // Ordenação pela data da próxima feita (revisão mais distante)
	public static Specification<FlashcardEntity> sortByNextReviewDesc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.desc(root.get("nextReview")));
			return null;
		};
	}

	// Ordenação pela data da próxima revisão (revisão mais próxima)
	public static Specification<FlashcardEntity> sortByNextReviewAsc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.asc(root.get("nextReview")));
			return null;
		};
	}
}
