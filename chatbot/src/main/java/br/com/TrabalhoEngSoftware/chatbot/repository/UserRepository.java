package br.com.TrabalhoEngSoftware.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
}
