package br.com.TrabalhoEngSoftware.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Long>, JpaSpecificationExecutor<NoteEntity>{
  void deleteByUserEntityId(Long userId);
}
