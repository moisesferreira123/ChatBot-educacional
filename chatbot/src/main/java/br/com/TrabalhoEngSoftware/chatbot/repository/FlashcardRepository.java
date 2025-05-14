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
  Page<FlashcardEntity> findNextDueFlashcard(@Param("deckId") Long deckId, @Param("userId") Long userId, @Param("tomorrow") LocalDateTime tomorrow, Pageable pageable);

}
