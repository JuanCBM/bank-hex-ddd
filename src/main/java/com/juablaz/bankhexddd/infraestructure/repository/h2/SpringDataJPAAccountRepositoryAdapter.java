package com.juablaz.bankhexddd.infraestructure.repository.h2;

import com.juablaz.bankhexddd.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;
import com.juablaz.bankhexddd.infraestructure.exception.AccountNotFoundException;
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
  public FullAccountResponseDto findById(Long id) {
    return mapper.map(accountRepository.findById(id).orElseThrow(AccountNotFoundException::new),
        FullAccountResponseDto.class);
  }

  @Override
  public Long save(FullAccountRequestDto fullAccountRequestDto) {
    AccountEntity account = mapper.map(fullAccountRequestDto, AccountEntity.class);
    this.accountRepository.save(account);

    return account.getId();
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
