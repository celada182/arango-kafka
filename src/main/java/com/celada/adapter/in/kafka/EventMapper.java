package com.celada.adapter.in.kafka;

import com.celada.domain.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class EventMapper {

  private ObjectMapper objectMapper;

  public Person execute(String body) throws InvalidEventException, IOException {
    if (body == null) {
      return null;
    }
    return objectMapper.readValue(body, Person.class);
  }

  public String execute(Person person) throws InvalidEventException, IOException {
    if (person == null) {
      return null;
    }
    return objectMapper.writeValueAsString(person);
  }
}
