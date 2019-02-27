package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
	SecurityAutoConfiguration.class,
	BatchAutoConfiguration.class,

	JpaRepositoriesAutoConfiguration.class
},
	scanBasePackages = {
	"com"
})
public class QuickHelpWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickHelpWebApplication.class, args);
	}
}
