package br.com.TrabalhoEngSoftware.chatbot.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;

public class DeckSpecificationBuilder {
  private Specification<DeckEntity> specification;

  public DeckSpecificationBuilder() {
    this.specification = Specification.where(null);
  }

  public DeckSpecificationBuilder filterByTitle(String title) {
    this.specification = this.specification.and(DeckSpecification.filterByTitle(title));
    return this;
  }

  public DeckSpecificationBuilder filterByTopic(String topic) {
    this.specification = this.specification.and(DeckSpecification.filterByTopic(topic));
    return this;
  }

  public DeckSpecificationBuilder sortByCreatedAtDesc() {
    this.specification = this.specification.and(DeckSpecification.sortByCreatedAtDesc());
    return this;
  }

  public DeckSpecificationBuilder sortByCreatedAtAsc() {
    this.specification = this.specification.and(DeckSpecification.sortByCreatedAtAsc());
    return this;
  }

  public DeckSpecificationBuilder sortByLastReviewedAtDesc() {
    this.specification = this.specification.and(DeckSpecification.sortByLastReviewedAtDesc());
    return this;
  }

  public DeckSpecificationBuilder sortByLastReviewedAtAsc() {
    this.specification = this.specification.and(DeckSpecification.sortByLastReviewedAtAsc());
    return this;
  }

  public DeckSpecificationBuilder sortByFlashcardsTotalDesc() {
    this.specification = this.specification.and(DeckSpecification.sortByFlashcardsTotalDesc());
    return this;
  }

  public DeckSpecificationBuilder sortByDueFlashcardsTotalDesc() {
    this.specification = this.specification.and(DeckSpecification.sortByDueFlashcardsTotalDesc());
    return this;
  }

  public DeckSpecificationBuilder sortByDueFlashcardsTotalAsc() {
    this.specification = this.specification.and(DeckSpecification.sortByDueFlashcardsTotalAsc());
    return this;
  }

  public DeckSpecificationBuilder sortByMasteryLevelDesc() {
    this.specification = this.specification.and(DeckSpecification.sortByMasteryLevelDesc());
    return this;
  }
  
  public DeckSpecificationBuilder sortByMasteryLevelAsc() {
    this.specification = this.specification.and(DeckSpecification.sortByMasteryLevelAsc());
    return this;
  }

  public Specification<DeckEntity> build(Long userId) {
    return this.specification.and((root, query, criteriaBuilder) -> 
      criteriaBuilder.equal(root.get("userEntity").get("id"), userId)
    );
  }
}
