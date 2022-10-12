package com.modsen.balancefromcard.repository;

import com.modsen.balancefromcard.model.Balance;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BalanceRepository extends MongoRepository<Balance, ObjectId> {

    Balance findByCardNumber(Long cardNumber);

}
