package br.com.TrabalhoEngSoftware.chatbot.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleObjectNotFound(ObjectNotFoundException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(InvalidObjectDataException.class)
  public ResponseEntity<Map<String, String>> handleInvalidObjectData(InvalidObjectDataException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(UnauthorizedObjectAccessException.class)
  public ResponseEntity<Map<String, String>> handleObjectFlashcardAccess(UnauthorizedObjectAccessException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
  }

  @ExceptionHandler(UnexpectedResponseException.class)
  public ResponseEntity<Map<String, String>> handleUnexpectedResponse(UnexpectedResponseException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(FileStorageException.class)
  public ResponseEntity<Map<String, String>> handleFileStorage(FileStorageException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(StorageInitializationException.class)
  public ResponseEntity<Map<String, String>> handleStorageInitialization(StorageInitializationException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(ObjectDeletionException.class)
  public ResponseEntity<Map<String, String>> handleObjectDeletion(ObjectDeletionException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(IncorrectPasswordException.class)
  public ResponseEntity<Map<String, String>> handleIncorrectPassword(IncorrectPasswordException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(PasswordMismatchException.class)
  public ResponseEntity<Map<String, String>> handlePasswordMismatch(PasswordMismatchException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(error);
  }

  @ExceptionHandler(AuthenticationFailureException.class)
  public ResponseEntity<Map<String, String>> handleAuthenticationFailure(AuthenticationFailureException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
  }

  @ExceptionHandler(UnexpectedFailureException.class)
  public ResponseEntity<Map<String, String>> handleUnexpectedFailure(UnexpectedFailureException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
