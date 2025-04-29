package br.com.TrabalhoEngSoftware.chatbot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.TrabalhoEngSoftware.chatbot.dto.NoteDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.repository.NoteRepository;
import br.com.TrabalhoEngSoftware.chatbot.specification.NoteSpecificationBuilder;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public NoteService(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	
	@Transactional
	public void createNote(NoteDTO noteDTO, Long userId) {
		NoteEntity note = new NoteEntity();
		note.setTitle(noteDTO.getTitle());
		note.setContent(noteDTO.getContent());
		note.setUserEntity(new UserEntity(userId));
		noteRepository.save(note);
	}
	
	public Page<NoteDTO> listNotes(String title, Long userId, String sortType, Pageable pageable) {
		NoteSpecificationBuilder builder = new NoteSpecificationBuilder().withTitle(title, userId);
		
		if ("createdAtAsc".equalsIgnoreCase(sortType)) {
            builder.sortByCreatedAtAsc();
        } else if ("createdAtDesc".equalsIgnoreCase(sortType)) {
            builder.sortByCreatedAtDesc();
        } else if ("updatedAtAsc".equalsIgnoreCase(sortType)) {
            builder.sortByUpdatedAtAsc();
        } else if ("updatedAtDesc".equalsIgnoreCase(sortType)) {
            builder.sortByUpdatedAtDesc();
        }
		
		Specification<NoteEntity> specification = builder.build();
		
		return noteRepository.findAll(specification, pageable).map(NoteDTO::new);
	}
	
	@Transactional
	public NoteDTO updateNoteTitle(Long noteId, String newTitle, Long userId) {
		// TODO: Trocar por Exceptions criados por nós
		NoteEntity note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
		if(!note.getUserEntity().getId().equals(userId)) {
			// TODO: Trocar por Exceptions criados por nós
			throw new RuntimeException("Unauthorized to edit title this note");
		}
		note.setTitle(newTitle);
		return new NoteDTO(noteRepository.save(note));
	}
	
	@Transactional
	public NoteDTO updateNoteContent(Long noteId, String newContent, Long userId) {
		// TODO: Trocar por Exceptions criados por nós
		NoteEntity note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
		if(!note.getUserEntity().getId().equals(userId)) {
			// TODO: Trocar por Exceptions criados por nós
			throw new RuntimeException("Unauthorized to edit content this note");
		}
		note.setContent(newContent);
		return new NoteDTO(noteRepository.save(note));
	}
	
	@Transactional
	public void deleteNote(Long noteId, Long userId) {
		// TODO: Trocar por Exceptions criados por nós
		NoteEntity note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
		if(!note.getUserEntity().getId().equals(userId)) {
			// TODO: Trocar por Exceptions criados por nós
			throw new RuntimeException("Unauthorized to delete this note");
		}
		noteRepository.delete(note);
	}
	
}
