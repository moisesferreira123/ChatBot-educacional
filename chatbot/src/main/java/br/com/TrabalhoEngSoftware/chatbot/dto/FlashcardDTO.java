package br.com.TrabalhoEngSoftware.chatbot.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;

public class FlashcardDTO {
	
	private Long id;
	private String front;
	private String back;
	private LocalDateTime createdAt;
	private LocalDateTime lastReviewAt;
	private LocalDateTime nextReview;
	private int repetition;
	private double easeFactor;
	private int interval;
	private FlashcardEntity flashcardEntity;
	
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
	
	public LocalDateTime getLastReviewAt() {
		return lastReviewAt;
	}
	
	public void setLastReviewAt(LocalDateTime lastReviewAt) {
		this.lastReviewAt = lastReviewAt;
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
	
	public FlashcardEntity getFlashcardEntity() {
		return flashcardEntity;
	}
	
	public void setFlashcardEntity(FlashcardEntity flashcardEntity) {
		this.flashcardEntity = flashcardEntity;
	}
}
