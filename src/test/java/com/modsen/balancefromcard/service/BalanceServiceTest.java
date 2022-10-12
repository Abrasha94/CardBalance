package com.modsen.balancefromcard.service;

import com.modsen.balancefromcard.dto.response.BalanceResponseDto;
import com.modsen.balancefromcard.exception.BalanceNotFoundException;
import com.modsen.balancefromcard.model.Balance;
import com.modsen.balancefromcard.repository.BalanceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class BalanceServiceTest {

    @Autowired
    BalanceService balanceService;

    @MockBean
    BalanceRepository balanceRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldThrowBalanceNotFoundException() {
        doReturn(null).when(balanceRepository).findByCardNumber(123L);
        assertThatThrownBy(() -> balanceService.findBalanceByCardNumber(123L)).isInstanceOf(BalanceNotFoundException.class).hasMessage("Balance not found!");
    }

    @Test
    void shouldReturnBalanceResponseDto() {
        doReturn(new Balance(BigDecimal.TEN, 12L, 32L)).when(balanceRepository).findByCardNumber(12L);
        assertThat(balanceService.findBalanceByCardNumber(12L)).isInstanceOf(BalanceResponseDto.class)
                .hasFieldOrPropertyWithValue("balance", BigDecimal.TEN);
    }

    @Test
    void msgListener() {
    }
}