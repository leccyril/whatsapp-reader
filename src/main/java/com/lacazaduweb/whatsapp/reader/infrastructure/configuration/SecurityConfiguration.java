package com.lacazaduweb.whatsapp.reader.infrastructure.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(
						requests ->
								requests.requestMatchers(HttpMethod.OPTIONS).permitAll()
										.requestMatchers(HttpMethod.GET, "/api/v1/version").permitAll()
										.requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
										.requestMatchers(HttpMethod.POST, "/api/v1/merchants/token").permitAll()
										.requestMatchers(HttpMethod.POST, "/api/v1/webhook").permitAll()
										.anyRequest()
										.authenticated())
				.formLogin(AbstractHttpConfigurer::disable)
				.logout(AbstractHttpConfigurer::disable)
				.httpBasic(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable)
				.build();
	}
}