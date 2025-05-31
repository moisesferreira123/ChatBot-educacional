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

import br.com.TrabalhoEngSoftware.chatbot.dto.DeckDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.DeckSummaryDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.service.DeckService;

@RestController
@RequestMapping("api/decks")
public class DeckController {

  @Autowired
  private DeckService deckService;

  @GetMapping
  public Page<DeckSummaryDTO> listDecks (
    @RequestParam(required = false) String title,
    @RequestParam(required = false) String topic,
    @RequestParam(defaultValue = "totalDueFlashcardsDesc") String sortType,
    Pageable pageable,
    Authentication authentication
  ) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return deckService.listDecks(title, topic, user.getId(), sortType, pageable);
  }

  @PostMapping
  public void createDeck(@RequestBody DeckDTO deckDTO, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    deckService.createDeck(deckDTO, user.getId());
  }

  @PutMapping("/{deckId}")
  public DeckSummaryDTO updateDeck(
    @PathVariable Long deckId,
    @RequestBody DeckSummaryDTO deckSummaryDTO,
    Authentication authentication
  ) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return deckService.updateDeck(deckId, deckSummaryDTO, user.getId());
  }

  @DeleteMapping("/{deckId}")
  public void deleteDeck(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    deckService.deleteDeck(deckId, user.getId());
  }

  @GetMapping("/get-flashcards-total/{deckId}")
  public long getFlashcardsTotal(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return deckService.getFlashcardsTotal(deckId, user.getId());
  }

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
