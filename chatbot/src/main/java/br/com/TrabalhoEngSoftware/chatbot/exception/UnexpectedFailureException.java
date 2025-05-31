package br.com.TrabalhoEngSoftware.chatbot.exception;

public class UnexpectedFailureException extends RuntimeException {
  public UnexpectedFailureException(String message) {
    super(message);
  }
}
