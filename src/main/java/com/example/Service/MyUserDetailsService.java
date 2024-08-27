package com.example.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Models.UserEntity;
import com.example.Repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	 
    @Override
    public UserDetails loadUserByUsername(String username){
    	System.out.println("Inside service loadUserByUsername " + username);
    	UserEntity user = userRepository.findByUsername(username);
    	System.out.println("User fetched: " + user);
        if (user == null) {
            //throw new UsernameNotFoundException("User not found with username: " + username);
        	System.out.println("User not found");
        	return null;
        }
        //user.get
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
        UserDetails ud = new User(user.getUsername(), user.getPassword(), authorities);
        return ud;
    }
}
