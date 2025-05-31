package br.com.TrabalhoEngSoftware.chatbot.exception;

public class IncorrectPasswordException extends RuntimeException {
  public IncorrectPasswordException(String message) {
    super(message);
  }
}
