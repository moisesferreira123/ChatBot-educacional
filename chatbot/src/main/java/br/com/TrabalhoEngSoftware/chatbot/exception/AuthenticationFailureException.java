package br.com.TrabalhoEngSoftware.chatbot.exception;

public class AuthenticationFailureException extends RuntimeException {
  public AuthenticationFailureException(String message) {
    super(message);
  }
}
