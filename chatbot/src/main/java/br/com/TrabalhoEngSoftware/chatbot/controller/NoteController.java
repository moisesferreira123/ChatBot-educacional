package br.com.TrabalhoEngSoftware.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrabalhoEngSoftware.chatbot.service.NoteService;

@RestController
@RequestMapping("api/notes")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
}
