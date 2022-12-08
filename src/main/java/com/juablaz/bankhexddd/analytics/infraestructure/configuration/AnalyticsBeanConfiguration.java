package com.juablaz.bankhexddd.analytics.infraestructure.configuration;

import com.juablaz.bankhexddd.analytics.domain.repository.AnalyticAccountRepository;
import com.juablaz.bankhexddd.analytics.domain.service.AnalyticAccountService;
import com.juablaz.bankhexddd.analytics.domain.service.AnalyticAccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyticsBeanConfiguration {

  @Bean
  public AnalyticAccountService analyticAccountService(
      AnalyticAccountRepository analyticAccountRepository) {
    return new AnalyticAccountServiceImpl(analyticAccountRepository);
  }

}