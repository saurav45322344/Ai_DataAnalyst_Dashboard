package com.Ai.Ai_Dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
		.csrf(csrf -> csrf.disable())  
		.cors(cors -> {})               
		.authorizeHttpRequests(auth -> auth
				.anyRequest().permitAll()  
				);

		return http.build();
	}

}






//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//		//.cors(cors -> {})
//		.csrf(csrf -> csrf.disable()) 
//		//.cors(cors -> {})
//		.authorizeHttpRequests(auth -> auth
//				.requestMatchers("/api/upload").permitAll()
//				 .requestMatchers("/api/ai/**").permitAll().anyRequest().authenticated()
//				 );
////				.anyRequest().permitAll()
////				.authenticated()
////				);
//
//		return http.build();
//
//	}