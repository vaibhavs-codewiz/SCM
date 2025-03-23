package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityCustomUserDetailService userDetailService;
   @Bean
   public AuthenticationProvider authenticationProvider()
   {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //user details services object
    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    //password encoder object
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
   }
   
   @Bean 
   public PasswordEncoder passwordEncoder()
   {
       return new BCryptPasswordEncoder();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
   {
    //which routes are protected and which are allowed configured here
    httpSecurity.authorizeHttpRequests(authorize->{
         authorize.requestMatchers("/user/**").authenticated();
         authorize.anyRequest().permitAll();
    });
    //configuration related to login form
    httpSecurity.formLogin(Customizer.withDefaults());
    return httpSecurity.build();
   }

}
