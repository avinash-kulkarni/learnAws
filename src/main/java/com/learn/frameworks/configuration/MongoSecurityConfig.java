package com.learn.frameworks.configuration;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class MongoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//adding session Mgmt to avoid passing auto session id
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.cors()
				.configurationSource(new CorsConfigurationSource() {

					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration corsConfiguration = new CorsConfiguration();
						corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
						corsConfiguration.setAllowCredentials(true);
						corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
						corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
						return corsConfiguration;
					}
				}).and()
		.csrf().disable()
		.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new JWTTokenFilter(), BasicAuthenticationFilter.class)
		.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
