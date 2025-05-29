package br.com.TrabalhoEngSoftware.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

import br.com.TrabalhoEngSoftware.chatbot.dto.FlashcardDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.FlashcardSummaryDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.service.FlashcardService;

@RestController
@RequestMapping("api/flashcards")
public class FlashcardController {
  
  @Autowired
  private FlashcardService flashcardService;

  @GetMapping
  public Page<FlashcardSummaryDTO> listFlashcards(
    @RequestParam(required = false) String word,
    @RequestParam(required = false) String flashcardFilter,
    @RequestParam(defaultValue = "lastReviewedAtDesc") String sortType,
    @RequestParam(required = true) Long deckId,
    Pageable pageable,
    Authentication authentication
  ) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.listFlashcards(word, flashcardFilter, user.getId(), deckId, sortType, pageable);
  }

  @PostMapping("/{deckId}")
  public void createFlashcard(@RequestBody FlashcardDTO flashcardDTO, @PathVariable Long deckId) {
    flashcardService.createFlashcard(flashcardDTO, deckId);
  }

  @PutMapping("/{flashcardId}")
  public void updateFlashcard(
    @PathVariable Long flashcardId, 
    @RequestBody FlashcardSummaryDTO flashcardSummaryDTO,
    Authentication authentication
  ) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    flashcardService.updateFlashcard(flashcardId, flashcardSummaryDTO, user.getId());
  }

  @DeleteMapping("/{flashcardId}")
  public void deleteFlashcard(@PathVariable Long flashcardId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    flashcardService.deleteFlashcard(flashcardId, user.getId());
  }

  @GetMapping("/{flashcardId}")
  public FlashcardSummaryDTO getFlashcardById(@PathVariable Long flashcardId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getFlashcardById(flashcardId, user.getId());
  }

  @GetMapping("/next-due-flashcard-by-deck-id/{deckId}")
  public ResponseEntity<FlashcardSummaryDTO> getNextDueFlashcardByDeckId(@PathVariable Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getNextDueFlashcardByDeckId(deckId, user.getId())
           .map(ResponseEntity::ok)
           .orElseGet(() -> ResponseEntity.noContent().build());
  }

  @PutMapping("/apply-review-result/{flashcardId}/{answer}")
  public void applyReviewResult(@PathVariable Long flashcardId, @PathVariable int answer, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    flashcardService.applyReviewResult(flashcardId, answer, user.getId());
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
}
