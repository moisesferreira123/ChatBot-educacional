package br.com.TrabalhoEngSoftware.chatbot.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.TrabalhoEngSoftware.chatbot.dto.NoteDTO;
import jakarta.persistence.CascadeType; // Import CascadeType
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany; // Import OneToMany
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_note")
@EntityListeners(AuditingEntityListener.class)
public class NoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column
	private String subtitle;

	@Lob
	@Column
	private String content;

	@CreatedDate
	@Column(nullable=false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity userEntity;

	@OneToMany(mappedBy = "noteEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SourceEntity> sources = new ArrayList<>();


	public NoteEntity(NoteDTO note) {
		BeanUtils.copyProperties(note, this);
	}

	public NoteEntity() {

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
		NoteEntity other = (NoteEntity) obj;
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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<SourceEntity> getSources() {
		return sources;
	}

	public void setSources(List<SourceEntity> sources) {
		this.sources = sources;
	}

	public void addSource(SourceEntity source) {
		sources.add(source);
		source.setNoteEntity(this);
	}

	public void removeSource(SourceEntity source) {
		sources.remove(source);
		source.setNoteEntity(null);
	}
}
