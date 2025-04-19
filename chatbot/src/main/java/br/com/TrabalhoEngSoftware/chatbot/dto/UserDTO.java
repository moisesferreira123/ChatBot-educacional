package br.com.TrabalhoEngSoftware.chatbot.dto;

import org.springframework.beans.BeanUtils;

import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;

public class UserDTO {
	
	private Long id;
	private String fullName;
	private String email;
	private String password;
	
	public UserDTO(UserEntity user) {
		BeanUtils.copyProperties(user, this);
	}
	
	public UserDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
