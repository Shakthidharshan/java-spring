package com.shakthi.spring_boot_practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shakthi.spring_boot_practice.Util.JwtFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	
	@Bean
	public PasswordEncoder passwordencoder() throws Exception{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtFilter jwtfilter) throws Exception {
	    http
	        .csrf(AbstractHttpConfigurer::disable)
	        .authorizeHttpRequests(auth -> auth
	        	.requestMatchers("/auth/**").permitAll()
	            .anyRequest().authenticated()  // All requests need authentication
	        )
	        .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

}
