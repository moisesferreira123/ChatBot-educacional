package br.com.TrabalhoEngSoftware.chatbot.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import br.com.TrabalhoEngSoftware.chatbot.service.AuthorizationService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.cors(Customizer.withDefaults())
//				.cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
		                .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
										.requestMatchers(HttpMethod.GET, "/api/**").authenticated()
    								.requestMatchers(HttpMethod.POST, "/api/**").authenticated()
    								.requestMatchers(HttpMethod.PUT, "/api/**").authenticated()   // ✅ isso aqui resolve
    								.requestMatchers(HttpMethod.DELETE, "/api/**").authenticated()
						.anyRequest().authenticated()
				)
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	    return configuration.getAuthenticationManager();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	AuthorizationService authorizationService;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(authorizationService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}

}
