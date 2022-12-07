package com.juablaz.bankhexddd.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;
import com.juablaz.bankhexddd.utils.AccountTestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AccountTest {

  private Account account;

  @BeforeEach
  public void before() {
    this.account = new Account(AccountTestConstants.USERNAME, AccountTestConstants.EUR);
    this.account.setBalance(new Money(AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR));
  }

  @Test
  public void withdrawSameCurrencyTest() {
    this.account.withdraw(new Money(AccountTestConstants.TEN, AccountTestConstants.EUR));
    assertEquals(
        AccountTestConstants.ONE_HUNDRED - AccountTestConstants.TEN,
        this.account.getBalance().getValue());
  }

  @Test
  public void withdrawDifferentCurrencyTest() {
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> this.account.withdraw(new Money(AccountTestConstants.TEN, AccountTestConstants.USD)));

    assertEquals(Money.INCOMPATIBLE_CURRENCY, illegalArgumentException.getMessage());
  }

  @Test
  public void withdrawMoreThanBalanceCurrencyTest() {
    this.account.withdraw(
        new Money(AccountTestConstants.ONE_HUNDRED + AccountTestConstants.TEN,
            AccountTestConstants.EUR));
    assertEquals(
        AccountTestConstants.ONE_HUNDRED - (AccountTestConstants.ONE_HUNDRED
            + AccountTestConstants.TEN),
        this.account.getBalance().getValue());
  }

  @Test
  public void depositSameCurrencyTest() {
    this.account.deposit(new Money(AccountTestConstants.TEN, AccountTestConstants.EUR));
    assertEquals(
        AccountTestConstants.ONE_HUNDRED + AccountTestConstants.TEN,
        this.account.getBalance().getValue());
  }

  @Test
  public void depositDifferentCurrencyTest() {
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> this.account.deposit(new Money(AccountTestConstants.TEN, AccountTestConstants.USD)));

    assertEquals(Money.INCOMPATIBLE_CURRENCY, illegalArgumentException.getMessage());
  }

  @Test
  public void ofTest() {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);

    Account account = Account.of(fullAccountResponseDto);

    assertEquals(fullAccountResponseDto.getId(), account.getId());
    assertEquals(fullAccountResponseDto.getValue(), account.getBalance().getValue());
    assertEquals(fullAccountResponseDto.getCurrency(), account.getBalance().getCurrency());
    assertEquals(fullAccountResponseDto.getName(), account.getName());

  }

}