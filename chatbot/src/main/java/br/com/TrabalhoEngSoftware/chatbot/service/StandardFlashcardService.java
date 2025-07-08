package br.com.TrabalhoEngSoftware.chatbot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import br.com.TrabalhoEngSoftware.chatbot.config.Constants;
import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
import br.com.TrabalhoEngSoftwareFramework.framework.exception.UnexpectedResponseException;
import br.com.TrabalhoEngSoftwareFramework.framework.service.FlashcardService;

public class StandardFlashcardService extends FlashcardService {
  
  public StandardFlashcardService() {
    super();
  }

  public void applyReview(StandardFlashcardEntity flashcard, int answer) {
    LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
    double easeFactor = flashcard.getEaseFactor();

    if(answer != Constants.WRONG && answer != Constants.HARD && answer != Constants.GOOD && answer != Constants.EASY) {
      throw new UnexpectedResponseException("Unexpected response");
    }

    if(answer == Constants.WRONG) {
      flashcard.setRepetition(0);
      if(LocalDateTime.now().plusMinutes(1L).isBefore(tomorrow)){
        flashcard.setNextReview(LocalDateTime.now().plusMinutes(1L));
      } else {
        flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
      }
      flashcard.setInterval(1);
      flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
    } else {
      flashcard.setRepetition(flashcard.getRepetition()+1);
      if(flashcard.getRepetition() == 1){
        if(answer == Constants.HARD) {
          if(LocalDateTime.now().plusMinutes(5L).isBefore(tomorrow)){
            flashcard.setNextReview(LocalDateTime.now().plusMinutes(5L));
          } else {
            flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
          }
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
        }
        if(answer == Constants.GOOD) { 
          if(LocalDateTime.now().plusMinutes(10L).isBefore(tomorrow)){
            flashcard.setNextReview(LocalDateTime.now().plusMinutes(10L));
          } else {
            flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
          }
        }
        if(answer == Constants.EASY) {
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
          flashcard.setInterval((int) Math.ceil(flashcard.getInterval()*flashcard.getEaseFactor()));
          flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
        }
      } else {
        if(flashcard.getInterval() == 1 && flashcard.getRepetition() == 2) {
          flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
        } else {
          flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
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
}
