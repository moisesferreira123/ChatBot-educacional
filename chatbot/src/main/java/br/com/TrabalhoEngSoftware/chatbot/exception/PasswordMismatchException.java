package br.com.TrabalhoEngSoftware.chatbot.exception;

public class PasswordMismatchException extends RuntimeException {
  public PasswordMismatchException(String message) {
    super(message);
  }
}
