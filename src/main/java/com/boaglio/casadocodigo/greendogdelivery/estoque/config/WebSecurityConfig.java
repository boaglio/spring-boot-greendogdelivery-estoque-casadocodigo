package com.boaglio.casadocodigo.greendogdelivery.estoque.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		 // libera arquivos em "/css","/js","/images","/webjars" e "favicon.ico" 
  	     .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
  	     // libera páginas home e API logs
		 .antMatchers("/", "/home","/api/logs").permitAll()
		 // todo o resto pede autenticação
		 .anyRequest().authenticated().and()
		 // HTTP Basic 
		 //.httpBasic();
		 // Form Login
		 // libera página de login
		 .formLogin().loginPage("/login").permitAll().and()
		 // libera página de logout
		 .logout().permitAll();
	}
	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		  UserDetails user = 
				  User.withUsername("fernando")
				  .password("{bcrypt}$2a$10$N/JkyAmIDX70am/U3PPP7uiWuRHH9VklzpjKP9ugAe2t6tAnNWLjq")
				  .roles("USER")
				  .build();

		return new InMemoryUserDetailsManager(user);
	}

	/**
	 * Gerador de senha
	 * @param args
	 */
	public static void main(String[] args) {
		String senhaAdmin = "boaglio123";
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println("senha = " + encoder.encode(senhaAdmin));
	}
	
}
