package br.com.TrabalhoEngSoftware.chatbot.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrabalhoEngSoftware.chatbot.dto.AuthenticationDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.RegisterDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.TokenDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.exception.AuthenticationFailureException;
import br.com.TrabalhoEngSoftware.chatbot.exception.InvalidObjectDataException;
import br.com.TrabalhoEngSoftware.chatbot.infra.security.TokenService;
import br.com.TrabalhoEngSoftware.chatbot.repository.UserRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		if(data.email() == null || data.email().isEmpty()) throw new InvalidObjectDataException("Email can't be empty");
		if(data.password() == null || data.password().isEmpty()) throw new InvalidObjectDataException("Password can't be empty");
		try {
			var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
			var auth = this.authenticationManager.authenticate(usernamePassword);
			var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
			return ResponseEntity.ok(new TokenDTO(token));
		} catch (Exception ex) {
			throw new AuthenticationFailureException("Invalid username or password");
    }
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
		if(data.fullName().trim().isEmpty()) throw new InvalidObjectDataException("Full name can't be empty");
		if(data.password().isEmpty()) throw new InvalidObjectDataException("Password can't be empty");
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		String fullName = data.fullName().trim();
		String[] username = fullName.split(" ");
		UserEntity newUser = new UserEntity(data.email(), username[0], fullName, encryptedPassword);
		this.userRepository.save(newUser);
		String token = tokenService.generateToken(newUser);
		return ResponseEntity.ok(new TokenDTO(token));
	}
}
