package br.com.TrabalhoEngSoftware.chatbot.dto;

import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;

public class UserDTO {
	
	private Long id;
	private String username;
	private String fullName;
	private String email;
	
	public UserDTO(UserEntity user) {
		this.id = user.getId();
		this.username = user.getUserName();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
	}
	
	public UserDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
