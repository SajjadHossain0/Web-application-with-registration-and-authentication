package com.authentication_web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.authentication_web.Entities.User;
import com.authentication_web.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {


	private UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	 @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        if (!user.isActive()) {
	            throw new LockedException("User account is blocked");
	        }

	        UserDetails thisUserDetails = org.springframework.security.core.userdetails.User
	                .withUsername(user.getEmail())
	                .password(user.getPassword())
	                .roles(user.getRole())
	                .build();

	        return thisUserDetails;
	    }
}
