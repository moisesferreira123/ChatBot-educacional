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
    @RequestParam(required = false) boolean dominatedFlashcards,
    @RequestParam(required = false) boolean undominatedFlashcards,
    @RequestParam(defaultValue = "lastReviewedAtDesc") String sortType,
    @RequestParam(required = true) Long deckId,
    Pageable pageable,
    Authentication authentication
  ) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.listFlashcards(word, dominatedFlashcards, undominatedFlashcards, user.getId(), deckId, sortType, pageable);
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

  @GetMapping("/next-due-flashcard")
  public ResponseEntity<FlashcardSummaryDTO> getNextDueFlashcard(Long deckId, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return flashcardService.getNextDueFlashcard(deckId, user.getId())
           .map(ResponseEntity::ok)
           .orElseGet(() -> ResponseEntity.noContent().build());
  }

  @PutMapping("apply-review-result/{flashcardId}")
  public void applyReviewResult(@PathVariable Long flashcardId, int answer, Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    flashcardService.applyReviewResult(flashcardId, answer, user.getId());
  }
}
