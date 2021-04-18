package com.ivoronline.springboot_dbauthentication.controllers;

import com.ivoronline.springboot_dbauthentication.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  @Autowired AccountRepository accountRepository;

  @Secured("ROLE_ADMIN")
  @RequestMapping("/Hello")
  public String hello() {
    return "Hello from Controller";
  }

}
