package br.com.TrabalhoEngSoftware.chatbot.specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Predicate;

public class DeckSpecification {
	// Filtro por título
	public static Specification<DeckEntity> filterByTitle(String title) {
		return (root, query, criteriaBuilder)-> {
			Predicate titlePredicate = criteriaBuilder.conjunction();
			if(title != null && !title.isEmpty()) {
				titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
			}
			return criteriaBuilder.and(titlePredicate);
		};
	}
	
	// Filtro por tópico
	public static Specification<DeckEntity> filterByTopic(String topic) {
		return (root, query, criteriaBuilder)-> {
			Predicate topicPredicate = criteriaBuilder.conjunction();
			if(topic != null && !topic.isEmpty()) {
				topicPredicate = criteriaBuilder.equal(root.get("topic"), topic);
			}
			return criteriaBuilder.and(topicPredicate);
		};
	}

	// Ordenação por data de criação mais recente
	public static Specification<DeckEntity> sortByCreatedAtDesc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
			return criteriaBuilder.conjunction();
		};
	}
	
  // Ordenação por data de criação mais antiga
	public static Specification<DeckEntity> sortByCreatedAtAsc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.asc(root.get("createdAt")));
			return null;
		};
	}

	// Ordenação pela data de última revisão feita (mais recente)
	public static Specification<DeckEntity> sortByLastReviewedAtDesc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.desc(root.get("lastReviewedAt")));
			return null;
		};
	}

	// Ordenação pela data de última revisão feita (mais antiga)
	public static Specification<DeckEntity> sortByLastReviewedAtAsc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.asc(root.get("lastReviewedAt")));
			return null;
		};
	}
	
	// Ordenação pela quantidade total de cards do deck (maior quantidade)
	public static Specification<DeckEntity> sortByFlashcardsTotalDesc() {
		return (root, query, criteriaBuilder) -> {
			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);
			query.groupBy(root.get("id"));
			query.orderBy(criteriaBuilder.desc(criteriaBuilder.count(flashcardJoin)));
			return null;		
		};
	}
	
	// Ordenação pela quantidade de cards pendentes para hoje (maior quantidade)
	public static Specification<DeckEntity> sortByDueFlashcardsTotalDesc() {
		return (root, query, criteriaBuilder) -> {
			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);

			LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay(); 
			Expression<Long> dueFlashcardsTotal = criteriaBuilder.sum(
				criteriaBuilder.<Long>selectCase()
				.when(criteriaBuilder.lessThan(flashcardJoin.get("nextReview"), tomorrow), 1L)
				.otherwise(0L)
			);

			query.groupBy(root.get("id"));
			query.orderBy(criteriaBuilder.desc(dueFlashcardsTotal));
			return null;
		};
	}

	// Ordenação pela quantidade de cards pendentes para hoje (menor quantidade)
	public static Specification<DeckEntity> sortByDueFlashcardsTotalAsc() {
		return (root, query, criteriaBuilder) -> {
			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);

			LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay(); 
			Expression<Long> dueFlashcardsTotal = criteriaBuilder.sum(
				criteriaBuilder.<Long>selectCase()
				.when(criteriaBuilder.lessThan(flashcardJoin.get("nextReview"), tomorrow), 1L)
				.otherwise(0L)
			);

			query.groupBy(root.get("id"));
			query.orderBy(criteriaBuilder.asc(dueFlashcardsTotal));
			return null;
		};
	}

	// TODO: Fazer as ordenações do nível de domínio
}
