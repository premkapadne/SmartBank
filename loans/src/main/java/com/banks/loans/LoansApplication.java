package com.banks.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.banks.loans.controller") })
@EnableJpaRepositories("com.banks.loans.repository")
@EntityScan("com.banks.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "SmartBank Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Prem Kapadne",
						email = "prmkapadne@gmail.com",
						url = "https://github.com/premkapadne"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/premkapadne"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "SmartBank Loans microservice REST API Documentation",
				url = "https://github.com/premkapadne"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
