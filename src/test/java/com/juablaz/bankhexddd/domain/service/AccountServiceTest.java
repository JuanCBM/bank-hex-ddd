package com.juablaz.bankhexddd.domain.service;

import static com.juablaz.bankhexddd.utils.AccountConstants.EUR;
import static com.juablaz.bankhexddd.utils.AccountConstants.ONE;
import static com.juablaz.bankhexddd.utils.AccountConstants.ONE_HUNDRED;
import static com.juablaz.bankhexddd.utils.AccountConstants.TEN;
import static com.juablaz.bankhexddd.utils.AccountConstants.USD;
import static com.juablaz.bankhexddd.utils.AccountConstants.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.juablaz.bankhexddd.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AccountServiceTest {

  @MockBean
  private AccountRepository accountRepository;

  private AccountService accountService;

  @Before
  public void before() {
    this.accountService = new AccountServiceImpl(this.accountRepository);
  }

  @Test
  public void createAndRetrieveAccountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(ONE, USERNAME,
        ONE_HUNDRED, EUR);

    Mockito.when(accountRepository.save(Mockito.any())).thenReturn(ONE);
    Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(fullAccountResponseDto);

    Long accountCreated = accountService.create(USERNAME, EUR);
    FullAccountResponseDto account = accountService.find(accountCreated);

    assertNotNull(account);
    assertEquals(USERNAME, account.getName());

  }

  @Test
  public void depositSameCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(ONE, USERNAME,
        ONE_HUNDRED, EUR);
    FullAccountRequestDto fullAccountRequestDto = new FullAccountRequestDto(ONE,
        ONE_HUNDRED + TEN, EUR);

    Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(fullAccountResponseDto);

    accountService.deposit(ONE, TEN, EUR);

    Mockito.verify(accountRepository).update(fullAccountRequestDto);

  }

  @Test(expected = IllegalArgumentException.class)
  public void depositDifferentCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(ONE, USERNAME,
        ONE_HUNDRED, EUR);

    Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(fullAccountResponseDto);
    accountService.deposit(ONE, TEN, USD);
  }

  @Test
  public void withdrawSameCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(ONE, USERNAME,
        ONE_HUNDRED, EUR);
    FullAccountRequestDto fullAccountRequestDto = new FullAccountRequestDto(ONE,
        ONE_HUNDRED - TEN, EUR);

    Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(fullAccountResponseDto);
    accountService.withdraw(ONE, TEN, EUR);

    Mockito.verify(accountRepository).update(fullAccountRequestDto);

  }

  @Test(expected = IllegalArgumentException.class)
  public void withdrawDifferentCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(ONE, USERNAME,
        ONE_HUNDRED, EUR);

    Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(fullAccountResponseDto);
    accountService.deposit(ONE, TEN, USD);
  }

}
/*
  // TODO: should reject withdraw more than balance, or maybe not?
*/