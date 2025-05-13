package br.com.TrabalhoEngSoftware.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;

public interface FlashcardRepository extends JpaRepository<FlashcardEntity, Long>, JpaSpecificationExecutor<FlashcardEntity>{
  
}
