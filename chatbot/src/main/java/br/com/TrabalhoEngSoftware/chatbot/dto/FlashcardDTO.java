package br.com.TrabalhoEngSoftware.chatbot.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;

public class FlashcardDTO {
	
	private Long id;
	private String front;
	private String back;
	private LocalDateTime createdAt;
	private LocalDateTime lastReviewedAt;
	private LocalDateTime nextReview;
	private int repetition;
	private double easeFactor;
	private int interval;
	private DeckEntity deckEntity;
	
	public FlashcardDTO(FlashcardEntity flashcard) {
		BeanUtils.copyProperties(flashcard, this);
	}
	
	public FlashcardDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFront() {
		return front;
	}
	
	public void setFront(String front) {
		this.front = front;
	}
	
	public String getBack() {
		return back;
	}
	
	public void setBack(String back) {
		this.back = back;
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
	
	public void setLastReviewedAt(LocalDateTime lastReviewAt) {
		this.lastReviewedAt = lastReviewAt;
	}
	
	public LocalDateTime getNextReview() {
		return nextReview;
	}
	
	public void setNextReview(LocalDateTime nextReview) {
		this.nextReview = nextReview;
	}
	
	public int getRepetition() {
		return repetition;
	}
	
	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}
	
	public double getEaseFactor() {
		return easeFactor;
	}
	
	public void setEaseFactor(double easeFactor) {
		this.easeFactor = easeFactor;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public DeckEntity getDeckEntity() {
		return deckEntity;
	}
	
	public void setFlashcardEntity(DeckEntity deckEntity) {
		this.deckEntity = deckEntity;
	}
}
