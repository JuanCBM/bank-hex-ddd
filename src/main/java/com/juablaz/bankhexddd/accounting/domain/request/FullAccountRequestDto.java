package com.juablaz.bankhexddd.accounting.domain.request;

import com.juablaz.bankhexddd.accounting.domain.Account;

public class FullAccountRequestDto {

  private String id;
  private Float value;
  private String currency;

  public FullAccountRequestDto(String id, Float value, String currency) {
    this.id = id;
    this.value = value;
    this.currency = currency;
  }

  public FullAccountRequestDto() {
  }

  public static FullAccountRequestDto of(Account account) {
    FullAccountRequestDto fullAccountRequestDto = new FullAccountRequestDto();
    fullAccountRequestDto.setId(account.getId());
    fullAccountRequestDto.setCurrency(account.getBalance().getCurrency());
    fullAccountRequestDto.setValue(account.getBalance().getValue());

    return fullAccountRequestDto;
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

    FullAccountRequestDto that = (FullAccountRequestDto) o;

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
