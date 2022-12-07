package com.juablaz.bankhexddd.application.response;

import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {

  private Long id;
  private String name;
  private Float value;
  private String currency;

  public static AccountResponseDto of(FullAccountResponseDto fullAccountResponseDto) {
    return AccountResponseDto.builder()
        .id(fullAccountResponseDto.getId())
        .name(fullAccountResponseDto.getName())
        .value(fullAccountResponseDto.getValue())
        .currency(fullAccountResponseDto.getCurrency())
        .build();
  }
}
