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

  @Autowired AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String enteredUsername) throws UsernameNotFoundException {

    //GET USER/ACCOUNT (From DB)
    Account  account = accountRepository.findByUsername(enteredUsername);

    //CHECK IF USER EXISTS
    if (account == null) { throw new UsernameNotFoundException(enteredUsername); } //Bad credentials

    //GET PASSWORD
    String storedPassword = account.password;

    //GET AUTHORITIES
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                           authorities.add(new SimpleGrantedAuthority(account.role));

    //CREATE USER DETAILS OBJECT
    UserDetails userDetails = new User(enteredUsername, storedPassword, authorities);

    //RETURN USER DETAILS OBJECT
    return userDetails ;

  }

}








