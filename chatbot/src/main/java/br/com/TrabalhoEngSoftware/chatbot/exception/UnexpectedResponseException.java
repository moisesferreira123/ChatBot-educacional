package br.com.TrabalhoEngSoftware.chatbot.exception;

public class UnexpectedResponseException extends RuntimeException {
  public UnexpectedResponseException(String message) {
    super(message);
  } 
}
