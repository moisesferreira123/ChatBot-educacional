package br.com.TrabalhoEngSoftware.chatbot.dto;

import java.time.LocalDateTime;

import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;

public class DeckSummaryDTO {

  private Long id;
	private String title;
	private String topic;
	private LocalDateTime createdAt;
	private LocalDateTime lastReviewedAt;

  public DeckSummaryDTO(DeckEntity deck) {
    this.id = deck.getId();
    this.title = deck.getTitle();
    this.topic = deck.getTopic();
    this.createdAt = deck.getCreatedAt();
    this.lastReviewedAt = deck.getLastReviewedAt();
  }

  public DeckSummaryDTO() {
    
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getLastReviewedAt() {
    return lastReviewedAt;
  }

  public void setLastReviewedAt(LocalDateTime lastReviewedAt) {
    this.lastReviewedAt = lastReviewedAt;
  }
  
}
