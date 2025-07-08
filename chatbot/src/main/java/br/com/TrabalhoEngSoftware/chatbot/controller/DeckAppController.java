package br.com.TrabalhoEngSoftware.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrabalhoEngSoftware.chatbot.service.DeckAppService;
import br.com.TrabalhoEngSoftwareFramework.framework.controller.DeckController;
import br.com.TrabalhoEngSoftwareFramework.framework.entity.UserEntity;

@RestController
@RequestMapping("api/decks")
public class DeckAppController extends DeckController {

  @Autowired
  private DeckAppService deckService;

  @GetMapping("/get-due-flashcards-total/{deckId}")
  public long getDueFlashcardsTotal(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return deckService.getDueFlashcardsTotal(deckId, user.getId());
  }

  @GetMapping("/get-mastery-level/{deckId}")
  public double getMasteryLevel(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return deckService.getMasteryLevel(deckId, user.getId());
  }
}
