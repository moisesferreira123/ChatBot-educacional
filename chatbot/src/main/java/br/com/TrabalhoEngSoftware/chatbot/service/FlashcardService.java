package br.com.TrabalhoEngSoftware.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.TrabalhoEngSoftware.chatbot.repository.FlashcardRepository;

@Service
public class FlashcardService {
  
  @Autowired
  private FlashcardRepository flashcardRepository;

  public FlashcardService(FlashcardRepository flashcardRepository) {
    this.flashcardRepository = flashcardRepository;
  }

  
}
