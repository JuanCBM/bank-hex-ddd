package com.juablaz.bankhexddd.application.response;

import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {

  public Long id;
  public String name;
  public String currency;

  public static AccountResponseDto of(FullAccountResponseDto fullAccountResponseDto) {

    return null;
//    return AccountResponseDto.builder()
//        .id(accountFullResponseDto.getId())
//        .name(accountFullResponseDto.getName())
//        .currency(accountFullResponseDto.getCurrency())
//        .build();
  }
}
