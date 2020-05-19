package com.celada.boot.config;

import com.celada.domain.EventService;
import com.celada.domain.EventUseCase;
import com.celada.domain.PersonRepository;
import com.celada.domain.RestUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public EventUseCase modelUseCase(PersonRepository personRepository) {
    return new EventUseCase(personRepository);
  }

  @Bean
  public RestUseCase restUseCase(EventService eventService) {
    return new RestUseCase(eventService);
  }
}
