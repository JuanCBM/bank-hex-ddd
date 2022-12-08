package com.juablaz.bankhexddd.analytics.infraestructure.repository.h2;

import com.juablaz.bankhexddd.analytics.domain.repository.AnalyticAccountRepository;
import com.juablaz.bankhexddd.analytics.domain.request.FullAccountAnalyticRequestDto;
import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import com.juablaz.bankhexddd.analytics.infraestructure.exception.AnalyticAccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SpringDataJPAAnalyticAccountRepositoryAdapter implements AnalyticAccountRepository {

  private final ModelMapper mapper = new ModelMapper();
  private final SpringDataJPAAnalyticAccountRepository analyticAccountRepository;

  public SpringDataJPAAnalyticAccountRepositoryAdapter(
      SpringDataJPAAnalyticAccountRepository analyticAccountRepository) {
    this.analyticAccountRepository = analyticAccountRepository;
  }

  @Override
  public List<FullAnalyticAccountResponseDto> findAccountsPerCurrency(String currency) {
    return this.analyticAccountRepository.findAccountsPerCurrency(currency).stream()
        .map(analyticAccountEntity -> new FullAnalyticAccountResponseDto
            (analyticAccountEntity.getAccountId(), analyticAccountEntity.getCurrency(),
                analyticAccountEntity.getAnalyticConnections().stream()
                    .map(analyticConnectionEntity -> analyticConnectionEntity.getStartedAt())
                    .collect(
                        Collectors.toList())))
        .collect(Collectors.toList());
  }

  @Override
  public void update(FullAccountAnalyticRequestDto fullAccountRequestDto) {
    this.analyticAccountRepository.save(AnalyticAccountEntity.of(fullAccountRequestDto));
  }

  @Override
  public FullAnalyticAccountResponseDto find(String accountId) {
    FullAnalyticAccountResponseDto fullAnalyticAccountResponseDto = new FullAnalyticAccountResponseDto();
    AnalyticAccountEntity analyticAccount =
        analyticAccountRepository.findById(accountId)
            .orElseThrow(AnalyticAccountNotFoundException::new);
    fullAnalyticAccountResponseDto.setAccountId(analyticAccount.getAccountId());
    fullAnalyticAccountResponseDto.setCurrency(analyticAccount.getCurrency());
    fullAnalyticAccountResponseDto.setAnnalyticConnections(analyticAccount.getAnalyticConnections()
        .stream().map(AnalyticConnectionEntity::getStartedAt).collect(
            Collectors.toList()));

    return fullAnalyticAccountResponseDto;

  }

  @Override
  public void create(FullAccountAnalyticRequestDto fullAccountAnalyticRequestDto) {
    this.analyticAccountRepository.save(
        AnalyticAccountEntity.of(fullAccountAnalyticRequestDto));
  }
}
