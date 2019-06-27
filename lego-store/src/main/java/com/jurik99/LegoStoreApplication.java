package com.jurik99;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.Mongobee;

@SpringBootApplication
public class LegoStoreApplication {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public LegoStoreApplication(final MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Bean
	public Mongobee mongobee() {
		final Mongobee mongobee = new Mongobee("mongodb://localhost:27017/legostore");
		mongobee.setMongoTemplate(mongoTemplate);
		mongobee.setChangeLogsScanPackage("com.jurik99.persistence");
		return mongobee;
	}

	public static void main(String[] args) {
        SpringApplication.run(LegoStoreApplication.class, args);
    }
}
