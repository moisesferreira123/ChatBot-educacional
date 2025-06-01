package br.com.TrabalhoEngSoftware.chatbot.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;

public interface FlashcardRepository extends JpaRepository<FlashcardEntity, Long>, JpaSpecificationExecutor<FlashcardEntity>{
  
  @Query("SELECT f FROM FlashcardEntity f WHERE f.deckEntity.id = :deckId AND f.deckEntity.userEntity.id = :userId AND f.nextReview < :tomorrow ORDER BY f.nextReview ASC")
  Page<FlashcardEntity> findNextDueFlashcardByDeckId(@Param("deckId") Long deckId, @Param("userId") Long userId, @Param("tomorrow") LocalDateTime tomorrow, Pageable pageable);

  @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.id = :deckId AND f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt IS NULL")
  long countNewFlashcards(@Param("deckId") Long deckId, @Param("userId") Long userId);

  @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.id = :deckId  AND f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt BETWEEN :startOfDay AND :endOfDay AND f.nextReview BETWEEN :startOfDay AND :endOfDay")
  long countLearningFlashcards(@Param("deckId") Long deckId, @Param("userId") Long userId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

  @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.id = :deckId  AND f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt < :startOfToday AND f.nextReview BETWEEN :startOfToday AND :endOfToday")
  long countReviewFlashcards(@Param("deckId") Long deckId, @Param("userId") Long userId, @Param("startOfToday") LocalDateTime startOfToday, @Param("endOfToday") LocalDateTime endOfToday);

  @Query("SELECT f FROM FlashcardEntity f WHERE f.deckEntity.userEntity.id = :userId AND f.nextReview < :tomorrow ORDER BY f.nextReview ASC")
  Page<FlashcardEntity> findNextDueFlashcard(@Param("userId") Long userId, @Param("tomorrow") LocalDateTime tomorrow, Pageable pageable);

  @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt IS NULL")
  long countAllNewFlashcards(@Param("userId") Long userId);

  @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt BETWEEN :startOfDay AND :endOfDay AND f.nextReview BETWEEN :startOfDay AND :endOfDay")
  long countAllLearningFlashcards(@Param("userId") Long userId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

  @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt < :startOfToday AND f.nextReview BETWEEN :startOfToday AND :endOfToday")
  long countAllReviewFlashcards(@Param("userId") Long userId, @Param("startOfToday") LocalDateTime startOfToday, @Param("endOfToday") LocalDateTime endOfToday);
}
