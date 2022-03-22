package com.learn.frameworks.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learn.frameworks.util.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTTokenFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			SecretKey key = Keys.hmacShaKeyFor(Constants.JWT_KEY.getBytes());
			String jwt = Jwts.builder().setIssuer("").setSubject("jwt token").claim("username", auth.getName())
					.claim("Authorization", getAuthorities(auth.getAuthorities()))
					.setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+300000000))
					.signWith(key).compact();
			response.setHeader(Constants.JWT_HDR, jwt);
		}
		filterChain.doFilter(request, response);
	}
	
	private String getAuthorities(Collection<? extends GrantedAuthority> collection ) {
		Set<String> authr = new HashSet<>();
		for(GrantedAuthority ga : collection) {
			authr.add(ga.getAuthority());
		}
		return String.join(",", authr);
	}

}
