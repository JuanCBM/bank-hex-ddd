package com.juablaz.bankhexddd.analytics.infraestructure.repository.h2;

import com.juablaz.bankhexddd.accounting.infraestructure.repository.h2.SpringDataJPAAccountRepository;
import com.juablaz.bankhexddd.analytics.domain.repository.AnalyticAccountRepository;
import com.juablaz.bankhexddd.analytics.domain.request.FullAccountAnalyticRequestDto;
import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SpringDataJPAAnalyticAccountRepositoryAdapter implements AnalyticAccountRepository {

  private final ModelMapper mapper = new ModelMapper();
  private final SpringDataJPAAccountRepository accountRepository;

  public SpringDataJPAAnalyticAccountRepositoryAdapter(
      SpringDataJPAAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public List<FullAnalyticAccountResponseDto> findAccountsPerCurrency(String currency) {
    return null;
  }

  @Override
  public void update(FullAccountAnalyticRequestDto fullAccountRequestDto) {

  }

  @Override
  public FullAnalyticAccountResponseDto find(String accountId) {
    return null;
  }

  @Override
  public void trackNewAccount(FullAccountAnalyticRequestDto fullAccountRequestDto) {

  }
}
