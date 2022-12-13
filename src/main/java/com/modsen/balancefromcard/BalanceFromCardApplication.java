package com.modsen.balancefromcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableDiscoveryClient
public class BalanceFromCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalanceFromCardApplication.class, args);
	}

}
