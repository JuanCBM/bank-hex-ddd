package com.juablaz.bankhexddd.domain.response;

public class FullAccountResponseDto {
  
  private Long id;
  private String name;
  private Float value;
  private String currency;

  public FullAccountResponseDto() {
  }

  public FullAccountResponseDto(Long id, String name, Float value, String currency) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.currency = currency;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

    FullAccountResponseDto that = (FullAccountResponseDto) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (name != null ? !name.equals(that.name) : that.name != null) {
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
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (currency != null ? currency.hashCode() : 0);
    return result;
  }
}
