package com.juablaz.bankhexddd.infraestructure.configuration;

import com.juablaz.bankhexddd.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.domain.service.AccountService;
import com.juablaz.bankhexddd.domain.service.AccountServiceImpl;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class BeanConfiguration {

  @Bean
  public AccountService accountService(AccountRepository accountRepositoryAdapter) {
    return new AccountServiceImpl(accountRepositoryAdapter);
  }

}