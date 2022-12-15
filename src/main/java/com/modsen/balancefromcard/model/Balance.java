package com.modsen.balancefromcard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "balances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    @Id
    private String id;

    private BigDecimal balance;
    @Indexed
    private Long cardNumber;
    private Long userId;
}
