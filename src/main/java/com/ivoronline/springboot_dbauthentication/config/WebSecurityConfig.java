package com.ivoronline.springboot_dbauthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //ALLOW ACCESS TO H2 CONSOLE
    httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();
    httpSecurity.headers().frameOptions().sameOrigin();
    httpSecurity.csrf().disable();

    //LOCK EVERYTHING ELSE (User must be Authorized with proper Roles & Authorities)
    httpSecurity.authorizeRequests().anyRequest().authenticated();

    //AUTHENTICATE USER WITH DEFAULT LOGIN FORM
    httpSecurity.formLogin();

  }

}
