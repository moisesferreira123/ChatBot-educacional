package br.com.TrabalhoEngSoftware.chatbot.dto;

import br.com.TrabalhoEngSoftwareFramework.framework.dto.UserAnswerDTO;

public class StandardUserAnswerDTO extends UserAnswerDTO {
  
  private int answer;

  public StandardUserAnswerDTO() {
    super();
  }

  public StandardUserAnswerDTO(Long flashcardId, String flashcardType, int answer) {
    super(flashcardId, flashcardType);
    this.answer = answer;
  }

  public int getAnswer() {
    return answer;
  }

  public void setAnswer(int answer) {
    this.answer = answer;
  }
}
