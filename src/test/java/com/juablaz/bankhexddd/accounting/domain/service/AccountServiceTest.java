package com.juablaz.bankhexddd.accounting.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.juablaz.bankhexddd.accounting.domain.Money;
import com.juablaz.bankhexddd.accounting.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.accounting.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;
import com.juablaz.bankhexddd.utils.AccountTestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class AccountServiceTest {

  @MockBean
  private AccountRepository accountRepository;

  private AccountService accountService;

  @BeforeEach
  void before() {
    this.accountService = new AccountServiceImpl(this.accountRepository);
  }

  @Test
  void createAndRetrieveAccountTest() {
    Mockito.doNothing().when(accountRepository).save(Mockito.any(FullAccountRequestDto.class));

    String accountCreated = accountService.create(AccountTestConstants.USERNAME,
        AccountTestConstants.EUR);

    assertNotNull(accountCreated);

  }

  @Test
  void retrieveAccountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);

    FullAccountResponseDto account = accountService.find(AccountTestConstants.ONE);

    assertNotNull(account);
    assertEquals(AccountTestConstants.USERNAME, account.getName());

  }

  @Test
  void depositSameCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    FullAccountRequestDto fullAccountRequestDto = new FullAccountRequestDto(
        AccountTestConstants.ONE,
        AccountTestConstants.ONE_HUNDRED + AccountTestConstants.TEN, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);

    accountService.deposit(AccountTestConstants.ONE, AccountTestConstants.TEN,
        AccountTestConstants.EUR);

    Mockito.verify(accountRepository).update(fullAccountRequestDto);

  }

  @Test
  void depositDifferentCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);

    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> accountService.deposit(AccountTestConstants.ONE, AccountTestConstants.TEN,
            AccountTestConstants.USD));

    assertEquals(Money.INCOMPATIBLE_CURRENCY, illegalArgumentException.getMessage());

  }

  @Test
  void withdrawSameCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    FullAccountRequestDto fullAccountRequestDto = new FullAccountRequestDto(
        AccountTestConstants.ONE,
        AccountTestConstants.ONE_HUNDRED - AccountTestConstants.TEN, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);
    accountService.withdraw(
        AccountTestConstants.ONE, AccountTestConstants.TEN, AccountTestConstants.EUR);

    Mockito.verify(accountRepository).update(fullAccountRequestDto);

  }

  @Test
  void withdrawMoreThanBalanceTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    FullAccountRequestDto fullAccountRequestDto = new FullAccountRequestDto(
        AccountTestConstants.ONE,
        AccountTestConstants.ONE_HUNDRED - (AccountTestConstants.ONE_HUNDRED
            + AccountTestConstants.TEN),
        AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);
    accountService.withdraw(
        AccountTestConstants.ONE, AccountTestConstants.ONE_HUNDRED + AccountTestConstants.TEN,
        AccountTestConstants.EUR);

    Mockito.verify(accountRepository).update(fullAccountRequestDto);

  }

  @Test
  void withdrawDifferentCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);

    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> accountService.withdraw(AccountTestConstants.ONE, AccountTestConstants.TEN,
            AccountTestConstants.USD));

    assertEquals(Money.INCOMPATIBLE_CURRENCY, illegalArgumentException.getMessage());

  }

}