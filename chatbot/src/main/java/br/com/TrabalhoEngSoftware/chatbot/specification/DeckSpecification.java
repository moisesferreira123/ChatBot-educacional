package br.com.TrabalhoEngSoftware.chatbot.specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.entity.DeckEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.specification.DeckSpecificationBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.ListJoin;

public class DeckSpecification extends DeckSpecificationBuilder {
	
	public DeckSpecification(String title, String topic) {
		super(title, topic);
	}

	@Override
	protected void registerAllSpecifications() {
		buildSpecification("totalDueFlashcardsDesc", (root, query, criteriaBuilder) -> {
			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);

			ListJoin<DeckEntity, StandardFlashcardEntity> standardFlashcardJoin =
          criteriaBuilder.treat(flashcardJoin, StandardFlashcardEntity.class);

			LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay(); 
			Expression<Long> dueFlashcardsTotal = criteriaBuilder.sum(
				criteriaBuilder.<Long>selectCase()
				.when(criteriaBuilder.lessThan(standardFlashcardJoin.get("nextReview"), tomorrow), 1L)
				.otherwise(0L)
			);

			query.groupBy(root.get("id"));
			query.orderBy(criteriaBuilder.desc(dueFlashcardsTotal));
			return null;	
		});

		buildSpecification("totalDueFlashcardsAsc", (root, query, criteriaBuilder) -> {
			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);

			ListJoin<DeckEntity, StandardFlashcardEntity> standardFlashcardJoin =
          criteriaBuilder.treat(flashcardJoin, StandardFlashcardEntity.class);

			LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay(); 
			Expression<Long> dueFlashcardsTotal = criteriaBuilder.sum(
				criteriaBuilder.<Long>selectCase()
				.when(criteriaBuilder.lessThan(standardFlashcardJoin.get("nextReview"), tomorrow), 1L)
				.otherwise(0L)
			);

			query.groupBy(root.get("id"));
			query.orderBy(criteriaBuilder.asc(dueFlashcardsTotal));
			return null;	
		});

		buildSpecification("masteryLevelDesc", (root, query, criteriaBuilder) -> {
			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);
			ListJoin<DeckEntity, StandardFlashcardEntity> standardFlashcardJoin =
				criteriaBuilder.treat(flashcardJoin, StandardFlashcardEntity.class);

			int repetitionMastery = 4;
			Expression<Long> dominatedFlashcards = criteriaBuilder.sum(
				criteriaBuilder.<Long>selectCase()
				.when(criteriaBuilder.greaterThanOrEqualTo(standardFlashcardJoin.get("repetition"), repetitionMastery), 1L)
				.otherwise(0L)
			);

			Expression<Long> flashcardsTotal = criteriaBuilder.count(standardFlashcardJoin);
			Expression<Number> masteryLevel = criteriaBuilder.quot(criteriaBuilder.toDouble(dominatedFlashcards), criteriaBuilder.toDouble(flashcardsTotal));
			query.groupBy(root.get("id"));
			query.orderBy(criteriaBuilder.desc(masteryLevel));
			return null;
		});

		buildSpecification("masteryLevelAsc", (root, query, criteriaBuilder) -> {
			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);
			ListJoin<DeckEntity, StandardFlashcardEntity> standardFlashcardJoin =
				criteriaBuilder.treat(flashcardJoin, StandardFlashcardEntity.class);

			int repetitionMastery = 4;
			Expression<Long> dominatedFlashcards = criteriaBuilder.sum(
				criteriaBuilder.<Long>selectCase()
				.when(criteriaBuilder.greaterThanOrEqualTo(standardFlashcardJoin.get("repetition"), repetitionMastery), 1L)
				.otherwise(0L)
			);

			Expression<Long> flashcardsTotal = criteriaBuilder.count(standardFlashcardJoin);
			Expression<Number> masteryLevel = criteriaBuilder.quot(criteriaBuilder.toDouble(dominatedFlashcards), criteriaBuilder.toDouble(flashcardsTotal));
			query.groupBy(root.get("id"));
			query.orderBy(criteriaBuilder.asc(masteryLevel));
			return null;
		});
	}
}
