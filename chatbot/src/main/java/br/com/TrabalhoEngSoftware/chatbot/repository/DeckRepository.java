package br.com.TrabalhoEngSoftware.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;

public interface DeckRepository extends JpaRepository<DeckEntity, Long>, JpaSpecificationExecutor<DeckEntity>{
  void deleteByUserEntityId(Long userId);
}
