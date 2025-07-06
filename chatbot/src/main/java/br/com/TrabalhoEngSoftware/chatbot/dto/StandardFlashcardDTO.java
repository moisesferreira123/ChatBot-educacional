package br.com.TrabalhoEngSoftware.chatbot.dto;

import java.time.LocalDateTime;

import br.com.TrabalhoEngSoftwareFramework.framework.dto.FlashcardDTO;

public class StandardFlashcardDTO extends FlashcardDTO {
	
	private String front;
	private String back;
	private LocalDateTime nextReview;
	private int repetition;
	private double easeFactor;
	private int interval;
	
	public StandardFlashcardDTO() {
		super();
		super.setFlashcardType("STANDARD_FLASHCARD");
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
}
