package com.ivoronline.springboot_dbauthentication.repositories;

import com.ivoronline.springboot_dbauthentication.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
  Account findByUsername(String Username);
}
