package com.modsen.balancefromcard.rest;

import com.modsen.balancefromcard.dto.response.BalanceResponseDto;
import com.modsen.balancefromcard.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/balance/")
public class BalanceControllerV1 {

    private final BalanceService balanceService;

    @Autowired
    public BalanceControllerV1(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("{cardNumber}")
    public Mono<ResponseEntity<BalanceResponseDto>> getBalanceByCardNumber(@PathVariable(name = "cardNumber") Long cardNumber) {
        return balanceService.findBalanceByCardNumber(cardNumber)
                .map(balanceResponseDto -> new ResponseEntity<>(balanceResponseDto, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
