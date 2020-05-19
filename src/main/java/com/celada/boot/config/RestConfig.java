package com.celada.boot.config;

import com.celada.adapter.in.rest.PersonController;
import com.celada.domain.RestUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

  @Bean
  public PersonController personController(RestUseCase restUseCase) {
    return new PersonController(restUseCase);
  }

}
