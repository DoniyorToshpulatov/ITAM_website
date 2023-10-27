package com.example;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class ItamWebsiteApplication implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(ItamWebsiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Flyway.configure()
				.baselineOnMigrate(true).dataSource(dataSource).load().migrate();
	}
}
