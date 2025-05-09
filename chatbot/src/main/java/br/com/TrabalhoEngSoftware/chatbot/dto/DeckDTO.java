package br.com.TrabalhoEngSoftware.chatbot.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.TrabalhoEngSoftware.chatbot.entity.DeckEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.FlashcardEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;

public class DeckDTO {
	
	private Long id;
	private String title;
	private String topic;
	private LocalDateTime createdAt;
	private LocalDateTime lastReviewedAt;
	private UserEntity userEntity;
	private List<FlashcardEntity> flashcards = new ArrayList<>();
	
	public DeckDTO(DeckEntity deck) {
		BeanUtils.copyProperties(deck, this);
	}
	
	public DeckDTO() {
		
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

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<FlashcardEntity> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(List<FlashcardEntity> flashcards) {
		this.flashcards = flashcards;
	}
	
	
}
