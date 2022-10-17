package com.modsen.balancefromcard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "balances")
@Data
public class Balance {

    @Id
    private String id;

    private BigDecimal balance;
    @Indexed
    private Long cardNumber;
    private Long userId;

    public Balance(String id,BigDecimal balance, Long cardNumber, Long userId) {
        super();
        this.id = id;
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.userId = userId;
    }
}
