package br.com.TrabalhoEngSoftware.chatbot.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;

public class FlashcardSpecificationBuilder {
  private Specification<FlashcardEntity> specification;

  public FlashcardSpecificationBuilder() {
    this.specification = Specification.where(null);
  }

  public FlashcardSpecificationBuilder filterByWord(String word) {
    this.specification = this.specification.and(FlashcardSpecification.filterByWord(word));
    return this;
  }

  public FlashcardSpecificationBuilder filterByDominatedFlashcards() {
    this.specification = this.specification.and(FlashcardSpecification.filterByDominatedFlashcards());
    return this;
  }

  public FlashcardSpecificationBuilder filterByUndominatedFlashcards() {
    this.specification = this.specification.and(FlashcardSpecification.filterByUndominatedFlashcards());
    return this;
  }

  public FlashcardSpecificationBuilder filterByNewFlashcards() {
    this.specification = this.specification.and(FlashcardSpecification.filterByNewFlashcards());
    return this;
  }

  public FlashcardSpecificationBuilder filterByNotNewFlashcards() {
    this.specification = this.specification.and(FlashcardSpecification.filterByNotNewFlashcards());
    return this;
  }

  public FlashcardSpecificationBuilder filterByDueFlashcards() {
    this.specification = this.specification.and(FlashcardSpecification.filterByDueFlashcards());
    return this;
  }

  public FlashcardSpecificationBuilder sortByCreatedAtDesc() {
    this.specification = this.specification.and(FlashcardSpecification.sortByCreatedAtDesc());
    return this;
  }

  public FlashcardSpecificationBuilder sortByCreatedAtAsc() {
    this.specification = this.specification.and(FlashcardSpecification.sortByCreatedAtAsc());
    return this;
  }

  public FlashcardSpecificationBuilder sortByLastReviewedAtDesc() {
    this.specification = this.specification.and(FlashcardSpecification.sortByLastReviewedAtDesc());
    return this;
  }

  public FlashcardSpecificationBuilder sortByLastReviewedAtAsc() {
    this.specification = this.specification.and(FlashcardSpecification.sortByLastReviewedAtAsc());
    return this;
  }

  public FlashcardSpecificationBuilder sortByNextReviewDesc() {
    this.specification = this.specification.and(FlashcardSpecification.sortByNextReviewDesc());
    return this;
  }

  public FlashcardSpecificationBuilder sortByNextReviewAsc() {
    this.specification = this.specification.and(FlashcardSpecification.sortByNextReviewAsc());
    return this;
  }

  public Specification<FlashcardEntity> build(Long userId, Long deckId) {
    return this.specification.and((root, query, criteriaBuilder) -> 
      criteriaBuilder.and(criteriaBuilder.equal(root.get("deckEntity").get("userEntity").get("id"), userId), criteriaBuilder.equal(root.get("deckEntity").get("id"), deckId))
    );
  }
}
