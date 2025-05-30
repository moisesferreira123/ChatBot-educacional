package br.com.TrabalhoEngSoftware.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrabalhoEngSoftware.chatbot.dto.UpdatePasswordDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.UserDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.service.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "api/users")
public class UserController {
	
	@Autowired	
	private UserService userService;
	
	@GetMapping
	public UserDTO getUserById(Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return userService.getUserById(user.getId());
	}

	@PutMapping("/update-user-informations")
	public UserDTO updateUserPersonalInformations(@RequestBody UserDTO userDTO, Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return userService.updateUserPersonalInformations(userDTO, user.getId());
	}

	@PutMapping("/update-user-password")
	public void updateUserPassword(@RequestBody UpdatePasswordDTO updatePasswordDTO, Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		userService.updateUserPassword(updatePasswordDTO, user.getId());
	}
	
	@DeleteMapping
	public void deleteUser(Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		userService.deleteUser(user.getId());
	}
}
