package ru.vitasoft.statusrouteservicelibrary;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "ru.vitasoft.statusrouteservicelibrary")
@EntityScan("ru.vitasoft")
public class MySpringApp {

}
