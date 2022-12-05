package com.juablaz.bankhexddd.domain;

import org.springframework.util.Assert;

// Value Object, enriquece el dominio. No es una entidad.
public class Money {

  public static final String INCOMPATIBLE_CURRENCY = "Incompatible currency";

  Float value;
  String currency;

  public Money(Float value, String currency) {
    this.value = value;
    this.currency = currency;
  }

  public Money() {

  }

  Money add(Money amount) {
    Assert.isTrue(this.sameCurrency(amount), INCOMPATIBLE_CURRENCY);
    return new Money(this.value + amount.value, this.currency);
  }

  Money subtract(Money amount) {
    Assert.isTrue(this.sameCurrency(amount), INCOMPATIBLE_CURRENCY);
    return new Money(this.value - amount.value, this.currency);
  }

  boolean sameCurrency(Money balance) {
    return this.currency.equals(balance.currency);
  }

  boolean isLessThan(Money amount) {
    Assert.isTrue(this.sameCurrency(amount), "Incompatible currency");
    return this.value < amount.value;
  }

  boolean isLessOrEqualThan(Money amount) {
    Assert.isTrue(this.sameCurrency(amount), "Incompatible currency");
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
