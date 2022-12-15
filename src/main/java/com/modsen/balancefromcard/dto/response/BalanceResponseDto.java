package com.modsen.balancefromcard.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.modsen.balancefromcard.model.Balance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
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
