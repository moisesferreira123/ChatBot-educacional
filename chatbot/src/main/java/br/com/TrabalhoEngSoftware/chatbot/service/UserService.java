package br.com.TrabalhoEngSoftware.chatbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.TrabalhoEngSoftware.chatbot.dto.UserDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> listAll() {
		List<UserEntity> users = userRepository.findAll();
		return users.stream().map(UserDTO::new).toList();
	}
	
	public void insert(UserDTO user) {
		UserEntity userEntity = new UserEntity(user);
		userRepository.save(userEntity);
	}
	
	public UserDTO update(UserDTO user) {
		UserEntity userEntity = new UserEntity(user);
		return new UserDTO(userRepository.save(userEntity));
	}
	
	public void delete(Long id) {
		UserEntity user = userRepository.findById(id).get();
		userRepository.delete(user);
	}
	
	public UserDTO searchById(Long id) {
		return new UserDTO(userRepository.findById(id).get());
	}
}
