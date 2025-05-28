package br.com.TrabalhoEngSoftware.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.TrabalhoEngSoftware.chatbot.dto.UserDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public 	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserDTO getUserById(Long userId) {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		return new UserDTO(user);
	}

	@Transactional
	public UserDTO update(UserDTO user) {
		UserEntity userEntity = new UserEntity(user);
		return new UserDTO(userRepository.save(userEntity));
	}
	
	@Transactional
	public void delete(Long id) {
		UserEntity user = userRepository.findById(id).get();
		userRepository.delete(user);
	}
	
	
}
