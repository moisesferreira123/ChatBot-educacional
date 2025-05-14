package br.com.TrabalhoEngSoftware.chatbot.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.TrabalhoEngSoftware.chatbot.dto.DeckDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_deck")
@EntityListeners(AuditingEntityListener.class)
public class DeckEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column
	private String topic;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column
	private LocalDateTime lastReviewedAt;

	@OneToMany(mappedBy = "deckEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FlashcardEntity> flashcards = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity userEntity;
	
	public DeckEntity(DeckDTO deck) {
		BeanUtils.copyProperties(deck, this);
	}
	
	public DeckEntity() {
		
	}

	public DeckEntity(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeckEntity other = (DeckEntity) obj;
		return Objects.equals(id, other.id);
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

	public List<FlashcardEntity> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(List<FlashcardEntity> flashcards) {
		this.flashcards = flashcards;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
}
