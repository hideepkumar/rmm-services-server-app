package com.example.rmmservicesserverapp;

import com.example.rmmservicesserverapp.model.User;
import com.example.rmmservicesserverapp.model.UserInfo;
import com.example.rmmservicesserverapp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class DBAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
    UserRepository userRepository;

	// @Transactional
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        User user = userRepository.findOneByUserName(name);

        if(user == null || !password.equals(user.getPassword())){
            throw new BadCredentialsException("Authentication failed for, " + name);
        }

        UserInfo userInfo = new UserInfo(user.getFirstName(), user);


        authentication = new UsernamePasswordAuthenticationToken(userInfo, password, new ArrayList<>());

        return authentication;
    }
	
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}