package com.example.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Models.UserEntity;
import com.example.Repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("Inside service loadUserByUsername " + username);
    	UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return (UserDetails) user;
        
    }
}