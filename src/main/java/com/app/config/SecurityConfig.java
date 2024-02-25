package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.JwtAuthFilter;
import com.app.security.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter authFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/auth/**").permitAll().and()
//				.authorizeHttpRequests().requestMatchers("/api/user/**").authenticated().and().authorizeHttpRequests()
//				.requestMatchers("/api/author/**").authenticated().and().authorizeHttpRequests()
//				.requestMatchers("/api/publisher/**").authenticated().and().authorizeHttpRequests()
//				.requestMatchers("/api/category/**").authenticated().and().authorizeHttpRequests()
//				.requestMatchers("/api/book/**").authenticated().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//				.authenticationProvider(authenticationProvider())
//				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
		
		return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/api/v1/fare/**","/api/v1/card/**","/qr/**","/api/v1/time/**","/api/user/**").authenticated();
                })
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}