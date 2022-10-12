package com.modsen.balancefromcard.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.modsen.balancefromcard.model.Balance;
import lombok.Data;

import java.math.BigDecimal;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResponseDto {

    private BigDecimal balance;
    private Long cardNumber;

    public static BalanceResponseDto fromBalance(Balance balance) {

        final BalanceResponseDto balanceResponseDto = new BalanceResponseDto();
        balanceResponseDto.setCardNumber(balance.getCardNumber());
        balanceResponseDto.setBalance(balance.getBalance());
        return balanceResponseDto;
    }
}
