package com.juablaz.bankhexddd.accounting.infraestructure.configuration;

import com.juablaz.bankhexddd.accounting.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.accounting.domain.service.AccountService;
import com.juablaz.bankhexddd.accounting.domain.service.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountingBeanConfiguration {

  @Bean
  public AccountService accountService(AccountRepository accountRepositoryAdapter) {
    return new AccountServiceImpl(accountRepositoryAdapter);
  }

}