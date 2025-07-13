package br.com.TrabalhoEngSoftware.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrabalhoEngSoftware.chatbot.service.FlashcardAppService;
import br.com.TrabalhoEngSoftwareFramework.framework.controller.FlashcardController;
import br.com.TrabalhoEngSoftwareFramework.framework.dto.FlashcardDTO;
import br.com.TrabalhoEngSoftwareFramework.framework.entity.UserEntity;

@RestController
@RequestMapping("api/flashcards")
public class FlashcardAppController extends FlashcardController {

  @Autowired
  private FlashcardAppService flashcardService;

  public static class GenerateFlashcardsRequest {
    private int count = 5; // Default count

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }
  }

  @GetMapping("/next-due-flashcard-by-deck-id/{deckId}")
  public ResponseEntity<FlashcardDTO> getNextDueFlashcardByDeckId(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getNextDueFlashcardByDeckId(deckId, user.getId())
           .map(ResponseEntity::ok)
           .orElseGet(() -> ResponseEntity.noContent().build());
  }

  @GetMapping("/get-count-new-flashcards/{deckId}")
  public long getCountNewFlashcards(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getCountNewFlashcards(deckId, user.getId());
  }

  @GetMapping("/get-count-learning-flashcards/{deckId}")
  public long getCountLearningFlashcards(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getCountLearningFlashcards(deckId, user.getId());
  }

  @GetMapping("/get-count-review-flashcards/{deckId}")
  public long getCountReviewFlashcards(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getCountReviewFlashcards(deckId, user.getId());
  }

  @GetMapping("/next-due-flashcard")
  public ResponseEntity<FlashcardDTO> getNextDueFlashcard(Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getNextDueFlashcard(user.getId())
           .map(ResponseEntity::ok)
           .orElseGet(() -> ResponseEntity.noContent().build());
  }

  @GetMapping("/get-count-all-new-flashcards")
  public long getCountAllNewFlashcards(Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getCountAllNewFlashcards(user.getId());
  }

  @GetMapping("/get-count-all-learning-flashcards")
  public long getCountAllLearningFlashcards(Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getCountAllLearningFlashcards(user.getId());
  }

  @GetMapping("/get-count-all-review-flashcards")
  public long getCountAllReviewFlashcards(Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getCountAllReviewFlashcards(user.getId());
  }
}
