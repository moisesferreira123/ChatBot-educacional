package br.com.TrabalhoEngSoftware.chatbot.dto;

import java.time.LocalDateTime;

import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;

public class NoteSummaryDTO {
	private Long id;
	private String title;
  private String subtitle;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;

  public NoteSummaryDTO(NoteEntity entity) {
  	this.id = entity.getId();
    this.title = entity.getTitle();
    this.subtitle = entity.getSubtitle();
    this.updatedAt = entity.getUpdatedAt();
    this.createdAt = entity.getCreatedAt();
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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
}
