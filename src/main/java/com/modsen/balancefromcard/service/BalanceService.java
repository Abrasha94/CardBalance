package com.modsen.balancefromcard.service;

import com.modsen.balancefromcard.dto.response.BalanceResponseDto;
import com.modsen.balancefromcard.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


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

    public Mono<BalanceResponseDto> findBalanceByCardNumber(Long cardNumber) {
        return balanceRepository.findByCardNumber(cardNumber).map(BalanceResponseDto::fromBalance);
    }

    @KafkaListener(topics = "balanceRequest")
    public void msgListener(String msg) {
        findBalanceByCardNumber(Long.valueOf(msg)).subscribe(result -> kafkaTemplate.send("balanceResponse", result));
    }
}
