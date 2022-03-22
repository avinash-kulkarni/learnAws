package com.learn.frameworks.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.frameworks.model.MyUser;
import com.learn.frameworks.repository.UserRepository;

/**
@Service
public class MyUserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<MyUser> myuser = userRepository.findByUsername(username);
		if(myuser.size() == 0) {
			throw new UsernameNotFoundException("User not found");
		}
		return new SecurityEmployee(myuser.get(0));
	}

}

**/
@Service
public class MyUserService implements AuthenticationProvider {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		List<MyUser> user = userRepository.findByUsername(authentication.getName());
		if(user.size() != 0) {
			if(passwordEncoder.matches(authentication.getCredentials().toString(), user.get(0).getPassword())) {
				List<GrantedAuthority> auth = new ArrayList<>();
				auth.add(new SimpleGrantedAuthority(user.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), auth);
			} else {
			 throw new BadCredentialsException("Invalid Password");
			}
			
		} else {
			 throw new BadCredentialsException("User doesnt exists");
			}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}