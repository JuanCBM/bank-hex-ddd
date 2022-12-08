package com.juablaz.bankhexddd.analytics.infraestructure.sink;

import com.juablaz.bankhexddd.analytics.domain.event.AccountCreatedEvent;
import com.juablaz.bankhexddd.analytics.infraestructure.repository.h2.AnalyticAccountEntity;
import com.juablaz.bankhexddd.analytics.infraestructure.repository.h2.SpringDataJPAAnalyticAccountRepository;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountProcessor {

  SpringDataJPAAnalyticAccountRepository springDataJPAAnalyticAccountRepository;

  public AccountProcessor(
      SpringDataJPAAnalyticAccountRepository springDataJPAAnalyticAccountRepository) {
    this.springDataJPAAnalyticAccountRepository = springDataJPAAnalyticAccountRepository;
  }

  @EventListener
  public void handleShoppingCartCreatedEvent(AccountCreatedEvent accountCreatedEvent) {
    AnalyticAccountEntity analyticAccountEntity = new AnalyticAccountEntity(
        accountCreatedEvent.getAccountId(),
        accountCreatedEvent.getCurrency(),
        new ArrayList<>());

    log.info("CREACION HECHA");

    this.springDataJPAAnalyticAccountRepository.save(analyticAccountEntity);

  }

}
