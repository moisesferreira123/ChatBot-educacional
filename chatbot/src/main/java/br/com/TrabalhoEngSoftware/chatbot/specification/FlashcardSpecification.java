package br.com.TrabalhoEngSoftware.chatbot.specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeSearchRegistry;
import br.com.TrabalhoEngSoftwareFramework.framework.specification.FlashcardSpecificationBuilder;

public class FlashcardSpecification extends FlashcardSpecificationBuilder {
  
	public FlashcardSpecification(FlashcardTypeSearchRegistry searchRegistry) {
		super(searchRegistry);
	}

	@Override
	protected void registerAllSpecifications() {
		buildSpecification("dominatedFlashcards", (root, query, criteriaBuilder) -> {
			int repetitionMastery = 4;
      return criteriaBuilder.greaterThanOrEqualTo(root.get("repetition"), repetitionMastery); 
		});

		buildSpecification("undominatedFlashcards", (root, query, criteriaBuilder) -> {
			int repetitionMastery = 4;
      return criteriaBuilder.lessThan(root.get("repetition"), repetitionMastery);
		});

		buildSpecification("newFlashcards", (root, query, criteriaBuilder) -> {
			return criteriaBuilder.isNull(root.get("lastReviewedAt"));
		});

		buildSpecification("notNewFlashcards", (root, query, criteriaBuilder) -> {
			return criteriaBuilder.isNotNull(root.get("lastReviewedAt"));
		});

		buildSpecification("dueFlashcards", (root, query, criteriaBuilder) -> {
      LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
			return criteriaBuilder.lessThan(root.get("nextReview"), tomorrow);
		});

		buildSpecification("nextReviewAsc", (root, query, criteriaBuilder) -> {
      query.orderBy(criteriaBuilder.asc(root.get("nextReview")));
			return null;
		});

		buildSpecification("nextReviewDesc", (root, query, criteriaBuilder) -> {
      query.orderBy(criteriaBuilder.desc(root.get("nextReview")));
			return null;
		});
	}
}
