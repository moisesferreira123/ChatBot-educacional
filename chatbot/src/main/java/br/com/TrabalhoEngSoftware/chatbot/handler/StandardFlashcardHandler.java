package br.com.TrabalhoEngSoftware.chatbot.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import br.com.TrabalhoEngSoftware.chatbot.config.Constants;
import br.com.TrabalhoEngSoftware.chatbot.dto.StandardFlashcardDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.StandardUserAnswerDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.exception.InvalidObjectDataException;
import br.com.TrabalhoEngSoftwareFramework.framework.exception.UnexpectedResponseException;
import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeHandler;

@Component
public class StandardFlashcardHandler implements FlashcardTypeHandler<StandardFlashcardDTO, StandardFlashcardEntity, StandardUserAnswerDTO> {
  
  @Override
  public String supportsType() {
    return "STANDARD_FLASHCARD";
  }

  @Override
  public StandardFlashcardEntity createFlashcard(StandardFlashcardDTO dto) {
    if(dto.getFront() == null || dto.getFront().trim().isEmpty()) {
      throw new InvalidObjectDataException("Front flashcard can't be empty");
    }

    StandardFlashcardEntity flashcard = new StandardFlashcardEntity();
    flashcard.setFront(dto.getFront());
    flashcard.setBack(dto.getBack());
    flashcard.setNextReview(LocalDateTime.now());
    flashcard.setRepetition(0);
    flashcard.setEaseFactor(2.5);
    flashcard.setInterval(1);
    return flashcard;
  }

  @Override
  public void updateFlashcard(StandardFlashcardEntity flashcard, StandardFlashcardDTO dto) {
    if(dto.getFront() == null || dto.getFront().trim().isEmpty()) {
      throw new InvalidObjectDataException("Front flashcard can't be empty");
    }

    flashcard.setFront(dto.getFront());

    if(dto.getBack() != null) {
      flashcard.setBack(dto.getBack());
    }
  }

  @Override
  public void evaluateAnswer(StandardFlashcardEntity flashcard, StandardUserAnswerDTO answer) {
    LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();

    double easeFactor = flashcard.getEaseFactor();

    if(answer.getAnswer() != Constants.WRONG && answer.getAnswer() != Constants.HARD && answer.getAnswer() != Constants.GOOD && answer.getAnswer() != Constants.EASY) {
      throw new UnexpectedResponseException("Unexpected response");
    }

    if(answer.getAnswer() == Constants.WRONG) {
      flashcard.setRepetition(0);
      if(LocalDateTime.now().plusMinutes(1L).isBefore(tomorrow)){
        flashcard.setNextReview(LocalDateTime.now().plusMinutes(1L));
      } else {
        flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
      }
      flashcard.setInterval(1);
      flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer.getAnswer()));
    } else {
      flashcard.setRepetition(flashcard.getRepetition()+1);
      if(flashcard.getRepetition() == 1){
        if(answer.getAnswer() == Constants.HARD) {
          if(LocalDateTime.now().plusMinutes(5L).isBefore(tomorrow)){
            flashcard.setNextReview(LocalDateTime.now().plusMinutes(5L));
          } else {
            flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
          }
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer.getAnswer()));
        }
        if(answer.getAnswer() == Constants.GOOD) { 
          if(LocalDateTime.now().plusMinutes(10L).isBefore(tomorrow)){
            flashcard.setNextReview(LocalDateTime.now().plusMinutes(10L));
          } else {
            flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
          }
        }
        if(answer.getAnswer() == Constants.EASY) {
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer.getAnswer()));
          flashcard.setInterval((int) Math.ceil(flashcard.getInterval()*flashcard.getEaseFactor()));
          flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
        }
      } else {
        if(flashcard.getInterval() == 1 && flashcard.getRepetition() == 2) {
          flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
        } else {
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer.getAnswer()));
          flashcard.setInterval((int) Math.ceil(flashcard.getInterval()*flashcard.getEaseFactor()));
          flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
        }
      }
    }

    flashcard.setLastReviewedAt(LocalDateTime.now());
    flashcard.getDeckEntity().setLastReviewedAt(LocalDateTime.now());
  }

  private double calculateEaseFactor(double easeFactor, int answer) {
    double easeFactorTemp = easeFactor - 0.8 + (0.28*answer) - (0.02*Math.pow(answer,2));
    return Math.max(Constants.MIN_EASE_FACTOR, easeFactorTemp);
  }

  @Override
  public StandardFlashcardDTO entityToDTO(StandardFlashcardEntity flashcard) {
    StandardFlashcardDTO dto = new StandardFlashcardDTO();
    dto.setId(flashcard.getId());
    dto.setFront(flashcard.getFront());
    dto.setBack(flashcard.getBack());
    dto.setCreatedAt(flashcard.getCreatedAt());
    dto.setLastReviewedAt(flashcard.getLastReviewedAt());
    dto.setNextReview(flashcard.getNextReview());
    dto.setRepetition(flashcard.getRepetition());
    dto.setEaseFactor(flashcard.getEaseFactor());
    dto.setInterval(flashcard.getInterval());
    return dto;
  }
}