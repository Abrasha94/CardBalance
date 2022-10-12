package com.modsen.balancefromcard.rest;

import com.modsen.balancefromcard.dto.response.BalanceResponseDto;
import com.modsen.balancefromcard.exception.BalanceNotFoundException;
import com.modsen.balancefromcard.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/balance/")
public class BalanceControllerV1 {

    private final BalanceService balanceService;

    @Autowired
    public BalanceControllerV1(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("{cardNumber}")
    public ResponseEntity<BalanceResponseDto> getBalanceByCardNumber(@PathVariable(name = "cardNumber") Long cardNumber) {
        try {
            final BalanceResponseDto balance = balanceService.findBalanceByCardNumber(cardNumber);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (BalanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
