package com.banks.accounts;

import com.banks.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.banks.accounts.controller") })
@EnableJpaRepositories("com.banks.accounts.repository")
@EntityScan("com.banks.accounts.model")*/
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "SmartBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Prem Kapadne",
						email = "prmkapadne@gmail.com",
						url = "https://github.com/premkapadne"
				),
				license = @License(
						name = "MIT Licence",
						url = "https://github.com/premkapadne"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "SmartBank Accounts microservice REST API Documentation",
				url = "https://github.com/premkapadne"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
