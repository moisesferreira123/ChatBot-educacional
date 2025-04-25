package br.com.TrabalhoEngSoftware.chatbot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Long>{
	// Método para buscar todas as notas paginadas
	Page<NoteEntity> findAllByUserId(Long userId, Pageable pageable);
}
