package com.example.rmmservicesserverapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration  
@EnableWebSecurity
@EnableScheduling
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {  
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Autowired
    private DBAuthenticationProvider dbAuthProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {  
        http 
        .csrf().disable()
        .headers().disable()
        .authenticationProvider(dbAuthProvider)
            .authorizeRequests()
            	.antMatchers("/").anonymous()
            	.antMatchers(
						"/getUserByName"
            			).permitAll()
            	.anyRequest().authenticated()
            	.and()
        	.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
        		.loginProcessingUrl("/processLogin")
        		.defaultSuccessUrl("/onLoggedIn", false)
        		.failureUrl("/login?error=true")
//        		.successHandler(cubeAuthenticationSuccessHandler())
        	.and().logout()
        	.logoutSuccessUrl("/")
        	.invalidateHttpSession(true);
//        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    @Override
    public void configure(WebSecurity security){
         security.ignoring().antMatchers("/css/**","/fonts/**","/libs/**", "/js/**" , "/resources/**", "/images/**", "/userImages/**");
    }

    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(dbAuthProvider);
    }
}