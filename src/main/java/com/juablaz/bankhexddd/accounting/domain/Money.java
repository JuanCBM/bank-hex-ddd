package com.juablaz.bankhexddd.accounting.domain;

import com.juablaz.bankhexddd.accounting.domain.exception.IncompatibleCurrencyException;

// Value Object, enriquece el dominio. No es una entidad.
public class Money {

  private Float value;
  private String currency;

  public Money(Float value, String currency) {
    this.value = value;
    this.currency = currency;
  }

  public Money() {

  }

  Money add(Money amount) throws IncompatibleCurrencyException {
    validateSameCurrency(amount);
    return new Money(this.value + amount.value, this.currency);
  }

  private void validateSameCurrency(Money amount) throws IncompatibleCurrencyException {
    if (!this.sameCurrency(amount)) {
      throw new IncompatibleCurrencyException();
    }
  }

  Money subtract(Money amount) throws IncompatibleCurrencyException {
    validateSameCurrency(amount);
    return new Money(this.value - amount.value, this.currency);
  }

  boolean sameCurrency(Money balance) {
    return this.currency.equals(balance.currency);
  }

  boolean isLessThan(Money amount) throws IncompatibleCurrencyException {
    validateSameCurrency(amount);
    return this.value < amount.value;
  }

  boolean isLessOrEqualThan(Money amount) throws IncompatibleCurrencyException {
    validateSameCurrency(amount);
    return this.value <= amount.value;
  }

  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
