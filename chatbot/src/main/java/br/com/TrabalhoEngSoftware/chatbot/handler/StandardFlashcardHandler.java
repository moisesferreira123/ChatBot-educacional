package br.com.TrabalhoEngSoftware.chatbot.handler;

import java.time.LocalDateTime;

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
  public int evaluateAnswer(StandardFlashcardEntity flashcard, StandardUserAnswerDTO answer) {
    int[] possiblesAnswer = {Constants.WRONG, Constants.HARD, Constants.GOOD, Constants.EASY};
    for(int possibleAnswer : possiblesAnswer){
      if(answer.getAnswer() == possibleAnswer) return answer.getAnswer();
    }
    throw new UnexpectedResponseException("This response is invalid.");
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