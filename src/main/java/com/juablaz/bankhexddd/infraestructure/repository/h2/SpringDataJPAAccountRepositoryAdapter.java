package com.juablaz.bankhexddd.infraestructure.repository.h2;

import com.juablaz.bankhexddd.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;
import com.juablaz.bankhexddd.infraestructure.exception.AccountNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SpringDataJPAAccountRepositoryAdapter implements AccountRepository {

  private final ModelMapper mapper = new ModelMapper();
  private final SpringDataJPAAccountRepository repository;

  public SpringDataJPAAccountRepositoryAdapter(SpringDataJPAAccountRepository repository) {
    this.repository = repository;
  }

  @Override
  public FullAccountResponseDto findById(Long id) {
    return mapper.map(repository.findById(id).orElseThrow(AccountNotFoundException::new),
        FullAccountResponseDto.class);
  }

  @Override
  public Long save(FullAccountRequestDto fullAccountRequestDto) {
    return null;
  }

  @Override
  public void update(FullAccountRequestDto fullAccountRequestDto) {

  }
}
