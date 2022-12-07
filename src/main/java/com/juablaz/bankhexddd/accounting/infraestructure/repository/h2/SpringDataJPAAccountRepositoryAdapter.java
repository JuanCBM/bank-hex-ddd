package com.juablaz.bankhexddd.accounting.infraestructure.repository.h2;

import com.juablaz.bankhexddd.accounting.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.accounting.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;
import com.juablaz.bankhexddd.accounting.infraestructure.exception.AccountNotFoundException;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SpringDataJPAAccountRepositoryAdapter implements AccountRepository {

  private final ModelMapper mapper = new ModelMapper();
  private final SpringDataJPAAccountRepository accountRepository;

  public SpringDataJPAAccountRepositoryAdapter(SpringDataJPAAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public FullAccountResponseDto findById(String id) {
    return mapper.map(accountRepository.findById(id).orElseThrow(AccountNotFoundException::new),
        FullAccountResponseDto.class);
  }

  @Override
  public void save(FullAccountRequestDto fullAccountRequestDto) {
    this.accountRepository.save(mapper.map(fullAccountRequestDto, AccountEntity.class));
  }

  @Override
  public void update(FullAccountRequestDto fullAccountRequestDto) {
    Optional<AccountEntity> account = accountRepository.findById(fullAccountRequestDto.getId());
    if (account.isPresent()) {
      account.get().setCurrency(fullAccountRequestDto.getCurrency());
      account.get().setValue(fullAccountRequestDto.getValue());

      this.accountRepository.save(account.get());
    }


  }
}
