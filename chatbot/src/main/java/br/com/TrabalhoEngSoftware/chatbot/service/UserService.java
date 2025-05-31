package br.com.TrabalhoEngSoftware.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.TrabalhoEngSoftware.chatbot.dto.UpdatePasswordDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.UserDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.exception.IncorrectPasswordException;
import br.com.TrabalhoEngSoftware.chatbot.exception.InvalidObjectDataException;
import br.com.TrabalhoEngSoftware.chatbot.exception.ObjectNotFoundException;
import br.com.TrabalhoEngSoftware.chatbot.exception.PasswordMismatchException;
import br.com.TrabalhoEngSoftware.chatbot.repository.DeckRepository;
import br.com.TrabalhoEngSoftware.chatbot.repository.NoteRepository;
import br.com.TrabalhoEngSoftware.chatbot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private DeckRepository deckRepository;

	public 	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserDTO getUserById(Long userId) {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found"));
		return new UserDTO(user);
	}

	@Transactional
	public UserDTO updateUserPersonalInformations(UserDTO userDTO, Long userId) {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found"));
		if(userDTO.getFullName() == null || userDTO.getFullName().trim().isEmpty()) {
      throw new InvalidObjectDataException("User full name can't be empty");
    }
		if(userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
      throw new InvalidObjectDataException("Username can't be empty");
    }
		if(userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
      throw new InvalidObjectDataException("User email can't be empty");
    }
		user.setFullName(userDTO.getFullName());
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		return new UserDTO(userRepository.save(user));
	}

	@Transactional
	public void updateUserPassword(UpdatePasswordDTO updatePasswordDTO, Long userId) {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found"));

		if(updatePasswordDTO.getCurrentPassword().isEmpty()) throw new InvalidObjectDataException("Current password can't be empty");
		if(updatePasswordDTO.getNewPassword().isEmpty()) throw new InvalidObjectDataException("New password can't be empty");
		if(updatePasswordDTO.getConfirmNewPassword().isEmpty()) throw new InvalidObjectDataException("New confirmation password can't be empty");

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(!passwordEncoder.matches(updatePasswordDTO.getCurrentPassword(), user.getPassword())) {
			throw new IncorrectPasswordException("Current password is incorrect");
		}
		if(!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmNewPassword())) throw new PasswordMismatchException("Passwords do not match");
		String newEncryptedPassword = new BCryptPasswordEncoder().encode(updatePasswordDTO.getNewPassword());
		user.setPassword(newEncryptedPassword);
		userRepository.save(user);
	}
	
	@Transactional
	public void deleteUser(Long userId) {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found"));
		noteRepository.deleteByUserEntityId(userId);
		deckRepository.deleteByUserEntityId(userId);
		userRepository.delete(user);
	}
}
