package com.juablaz.bankhexddd.accounting.application.dto.response;

import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {

  private String id;
  private String name;
  private Float value;
  private String currency;

  public static AccountResponseDto of(FullAccountResponseDto fullAccountResponseDto) {
    return new AccountResponseDto(fullAccountResponseDto.getId(),
        fullAccountResponseDto.getName(),
        fullAccountResponseDto.getValue(),
        fullAccountResponseDto.getCurrency()
    );
  }
}
