package com.modsen.balancefromcard.service;

import com.modsen.balancefromcard.dto.response.BalanceResponseDto;
import com.modsen.balancefromcard.exception.BalanceNotFoundException;
import com.modsen.balancefromcard.model.Balance;
import com.modsen.balancefromcard.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@EnableKafka
@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final KafkaTemplate<String, BalanceResponseDto> kafkaTemplate;

    @Autowired
    public BalanceService(BalanceRepository balanceRepository, KafkaTemplate<String, BalanceResponseDto> kafkaTemplate) {
        this.balanceRepository = balanceRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public BalanceResponseDto findBalanceByCardNumber(Long cardNumber) {
        final Balance balance = balanceRepository.findByCardNumber(cardNumber);
        if (balance == null) {
            throw new BalanceNotFoundException("Balance not found!");
        } else {
            return BalanceResponseDto.fromBalance(balance);
        }
    }

    @KafkaListener(topics = "balanceRequest")
    public void msgListener(String msg) {
        final BalanceResponseDto balance = findBalanceByCardNumber(Long.valueOf(msg));
        kafkaTemplate.send("balanceResponse", balance);
    }
}
