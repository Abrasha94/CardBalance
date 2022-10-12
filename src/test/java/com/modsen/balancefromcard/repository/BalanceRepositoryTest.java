package com.modsen.balancefromcard.repository;

import com.modsen.balancefromcard.model.Balance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BalanceRepositoryTest {

    @Autowired
    BalanceRepository balanceRepository;

    final Balance balance = new Balance(BigDecimal.TEN, 123456L, 321L);
    List<Balance> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        list.add(balance);
        balanceRepository.save(balance);
    }

    @AfterEach
    void tearDown() {
        balanceRepository.delete(balance);
    }

    @Test
    void shouldReturnBalance() {
        assertThat(balanceRepository.findByCardNumber(123456L)).isEqualTo(balance);
    }

    @Test
    void shouldBeNotEmpty() {
        assertThat(balanceRepository.findAll()).isNotEmpty();
    }
}