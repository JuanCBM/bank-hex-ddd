package com.juablaz.bankhexddd.accounting.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.juablaz.bankhexddd.accounting.domain.exception.IncompatibleCurrencyException;
import com.juablaz.bankhexddd.accounting.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.accounting.domain.request.AccountRequestDto;
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
  void depositSameCurrencyAmountTest() throws IncompatibleCurrencyException {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    AccountRequestDto accountRequestDto = new AccountRequestDto(
        AccountTestConstants.ONE,
        AccountTestConstants.ONE_HUNDRED + AccountTestConstants.TEN, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);

    accountService.deposit(AccountTestConstants.ONE, AccountTestConstants.TEN,
        AccountTestConstants.EUR);

    Mockito.verify(accountRepository).update(accountRequestDto);

  }

  @Test
  void depositDifferentCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);

    IncompatibleCurrencyException incompatibleCurrencyException = assertThrows(
        IncompatibleCurrencyException.class,
        () -> accountService.deposit(AccountTestConstants.ONE, AccountTestConstants.TEN,
            AccountTestConstants.USD));

    assertEquals(IncompatibleCurrencyException.INCOMPATIBLE_CURRENCY,
        incompatibleCurrencyException.getMessage());

  }

  @Test
  void withdrawSameCurrencyAmountTest() throws IncompatibleCurrencyException {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    AccountRequestDto accountRequestDto = new AccountRequestDto(
        AccountTestConstants.ONE,
        AccountTestConstants.ONE_HUNDRED - AccountTestConstants.TEN, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);
    accountService.withdraw(
        AccountTestConstants.ONE, AccountTestConstants.TEN, AccountTestConstants.EUR);

    Mockito.verify(accountRepository).update(accountRequestDto);

  }

  @Test
  void withdrawMoreThanBalanceTest() throws IncompatibleCurrencyException {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    AccountRequestDto accountRequestDto = new AccountRequestDto(
        AccountTestConstants.ONE,
        AccountTestConstants.ONE_HUNDRED - (AccountTestConstants.ONE_HUNDRED
            + AccountTestConstants.TEN),
        AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);
    accountService.withdraw(
        AccountTestConstants.ONE, AccountTestConstants.ONE_HUNDRED + AccountTestConstants.TEN,
        AccountTestConstants.EUR);

    Mockito.verify(accountRepository).update(accountRequestDto);

  }

  @Test
  void withdrawDifferentCurrencyAmountTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);

    Mockito.when(accountRepository.findById(Mockito.anyString()))
        .thenReturn(fullAccountResponseDto);

    IncompatibleCurrencyException incompatibleCurrencyException = assertThrows(
        IncompatibleCurrencyException.class,
        () -> accountService.withdraw(AccountTestConstants.ONE, AccountTestConstants.TEN,
            AccountTestConstants.USD));

    assertEquals(IncompatibleCurrencyException.INCOMPATIBLE_CURRENCY,
        incompatibleCurrencyException.getMessage());

  }

}