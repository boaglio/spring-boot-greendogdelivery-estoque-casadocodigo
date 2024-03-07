package com.boaglio.casadocodigo.greendogdelivery.estoque.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig   {


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						// libera arquivos em "/css","/js","/images","/webjars" e "favicon.ico"
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						// libera páginas home e API logs
						.requestMatchers("/", "/home", "/api/logs").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll()
				)
				.logout(LogoutConfigurer::permitAll);

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		  var user =
				  User.withUsername("fernando")
				  .password("{bcrypt}$2a$10$N/JkyAmIDX70am/U3PPP7uiWuRHH9VklzpjKP9ugAe2t6tAnNWLjq")
				  .roles("USER")
				  .build();

		return new InMemoryUserDetailsManager(user);
	}

	/**
	 * Gerador de senha
     */
	public static void main(String[] args) {
		var senhaAdmin = "boaglio123";
		var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println("senha = " + encoder.encode(senhaAdmin));
	}
	
}
