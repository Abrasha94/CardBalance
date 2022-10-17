package com.modsen.balancefromcard.repository;

import com.modsen.balancefromcard.model.Balance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BalanceRepositoryTest {

    @Autowired
    BalanceRepository balanceRepository;

    final Balance balance = new Balance("1", BigDecimal.TEN, 123456L, 321L);

    @BeforeEach
    void setUp() {
        balanceRepository.save(balance).block();
    }

    @AfterEach
    void tearDown() {
        balanceRepository.delete(balance).block();
    }

    @Test
    void shouldReturnBalance() {
        final Mono<Balance> balanceMono = balanceRepository.findByCardNumber(123456L);
        StepVerifier
                .create(balanceMono)
                .assertNext(balance1 -> {
                    assertThat(balance1.getId()).isEqualTo("1");
                    assertThat(balance1.getBalance()).isEqualTo(BigDecimal.TEN);
                    assertThat(balance1.getUserId()).isEqualTo(321L);
                })
                .expectComplete()
                .verify();
    }

    @Test
    void shouldBeNotEmpty() {
        final Flux<Balance> flux = balanceRepository.findAll();
        StepVerifier
                .create(flux)
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }
}