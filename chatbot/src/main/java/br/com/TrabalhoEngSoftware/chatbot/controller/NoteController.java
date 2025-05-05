package br.com.TrabalhoEngSoftware.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrabalhoEngSoftware.chatbot.dto.NoteDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.NoteSummaryDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.NoteUpdateDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.service.NoteService;

@RestController
@RequestMapping("api/notes")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@GetMapping("/{noteId}")
	public NoteDTO getNoteById(@PathVariable long noteId, Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return noteService.getNoteById(noteId, user.getId());
	}
	
	@GetMapping
	public Page<NoteSummaryDTO> listNotes(
			@RequestParam(required = false) String title, 
			@RequestParam(defaultValue = "updatedAtDesc") String sortType,
			Pageable pageable,
			Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return noteService.listNotes(title, user.getId(), sortType, pageable);
	}
	
	@PostMapping
	public Long createNote(@RequestBody NoteDTO noteDTO, Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return noteService.createNote(noteDTO, user.getId());
	}
	
	@PutMapping("/{noteId}")
	public NoteDTO updateNote(
			@PathVariable Long noteId,
			@RequestBody NoteUpdateDTO updateDTO,
			Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return noteService.updateNote(noteId, updateDTO, user.getId());
	}
	
	@DeleteMapping("/{noteId}")
	public void deleteNote(@PathVariable Long noteId, Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		noteService.deleteNote(noteId, user.getId());
	}
	
}
