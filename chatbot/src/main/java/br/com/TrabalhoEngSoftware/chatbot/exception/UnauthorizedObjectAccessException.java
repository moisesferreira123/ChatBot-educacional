package br.com.TrabalhoEngSoftware.chatbot.exception;

public class UnauthorizedObjectAccessException extends RuntimeException {
  public UnauthorizedObjectAccessException(String message) {
    super(message);
  }
}
