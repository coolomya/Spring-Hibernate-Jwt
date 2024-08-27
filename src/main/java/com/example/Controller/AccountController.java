package com.example.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import com.example.Models.*;
import com.example.Service.*;
import com.example.Util.JwtUtil;
import com.example.exception.CustomAuthorizationException;

import org.springframework.security.access.prepost.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;

import java.util.List;

import javax.validation.*;


@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

    @GetMapping("/getAccount/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable @Size(min = 1) int id) {
    	AccountDTO account;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                 
        	account = accountService.getAccountById(id);
        } else {
        	 throw new CustomAuthorizationException("User does not have the required access");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }
    
    
    @PostMapping("/addAccount")
    public ResponseEntity<AccountDTO> addAccount(@Valid @RequestBody AccountDTO account) {
        
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                	
        accountService.createAccount(account);
        } else {
       	 throw new CustomAuthorizationException("User does not have the required access");
         
       }
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }
    
    @PatchMapping("/updateAccount")
    public ResponseEntity<AccountDTO> updateAccount(@Valid @RequestBody AccountDTO account) {
        
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
           
        accountService.updateAccount(account.getAccountId(), account);
        } else {
          	 throw new CustomAuthorizationException("User does not have the required access");
            
          }
        return ResponseEntity.status(HttpStatus.OK).body(account);
        
    }
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserEntity authenticationRequest){

    	System.out.println("Inside authenticate");
		try {
			System.out.println("inside try");
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			System.out.println("Incorrect username or password");
			e.printStackTrace();
		}

		System.out.println("B4 loadUserByUsername");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		if (userDetails == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not found");
		}

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(jwt);
	}


}
