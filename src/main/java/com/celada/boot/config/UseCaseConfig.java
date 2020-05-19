package com.celada.boot.config;

import com.celada.domain.PersonRepository;
import com.celada.domain.PersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public PersonUseCase modelUseCase(PersonRepository personRepository) {
    return new PersonUseCase(personRepository);
  }

}
