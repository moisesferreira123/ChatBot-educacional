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

  public DeckSpecificationBuilder sortByTotalFlashcardsDesc() {
    this.specification = this.specification.and(DeckSpecification.sortByTotalFlashcardsDesc());
    return this;
  }

  public DeckSpecificationBuilder sortByTotalDueFlashcardsDesc() {
    this.specification = this.specification.and(DeckSpecification.sortByTotalDueFlashcardsDesc());
    return this;
  }

  public DeckSpecificationBuilder sortByTotalDueFlashcardsAsc() {
    this.specification = this.specification.and(DeckSpecification.sortByTotalDueFlashcardsAsc());
    return this;
  }

  public Specification<DeckEntity> build(Long userId) {
    return this.specification.and((root, query, criteriaBuilder) -> 
      criteriaBuilder.equal(root.get("userEntity").get("id"), userId)
    );
  }
}
