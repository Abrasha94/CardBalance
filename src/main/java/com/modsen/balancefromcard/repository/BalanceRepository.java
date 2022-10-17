package com.modsen.balancefromcard.repository;

import com.modsen.balancefromcard.model.Balance;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface BalanceRepository extends ReactiveMongoRepository<Balance, String> {

    Mono<Balance> findByCardNumber(Long cardNumber);

}
