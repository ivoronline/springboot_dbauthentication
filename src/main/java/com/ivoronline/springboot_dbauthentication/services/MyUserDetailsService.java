package com.ivoronline.springboot_dbauthentication.services;

import com.ivoronline.springboot_dbauthentication.entities.Account;
import com.ivoronline.springboot_dbauthentication.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    //GET ACCOUNT FROM DB
    Account account  = accountRepository.findByUsername(username);
    String  password = account.password;

    //CREATE AUTHORITIES
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                           authorities.add(new SimpleGrantedAuthority(account.role));

    //CREATE USER
    User user = new User(username, password, authorities);

    //RETURN USER
    return user;

  }

}








