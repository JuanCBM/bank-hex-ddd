package com.juablaz.bankhexddd.accounting.domain.request;

import com.juablaz.bankhexddd.accounting.domain.Account;

public class AccountRequestDto {

  private String id;
  private Float value;
  private String currency;

  public AccountRequestDto(String id, Float value, String currency) {
    this.id = id;
    this.value = value;
    this.currency = currency;
  }

  public AccountRequestDto() {
  }

  public static AccountRequestDto of(Account account) {
    AccountRequestDto accountRequestDto = new AccountRequestDto();
    accountRequestDto.setId(account.getId());
    accountRequestDto.setCurrency(account.getBalance().getCurrency());
    accountRequestDto.setValue(account.getBalance().getValue());

    return accountRequestDto;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AccountRequestDto that = (AccountRequestDto) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (value != null ? !value.equals(that.value) : that.value != null) {
      return false;
    }
    return currency != null ? currency.equals(that.currency) : that.currency == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (currency != null ? currency.hashCode() : 0);
    return result;
  }
}
