package com.celada.domain;

import com.celada.domain.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Component
public class RestUseCase {

  private EventService eventService;

  public void create(Person person) throws ExecutionException, InterruptedException, JsonProcessingException {
    eventService.create(person);
  }

  public Person read(String name) throws ExecutionException, InterruptedException, IOException {
    return eventService.read(name);
  }

}
