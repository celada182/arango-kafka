package com.celada.domain;

import com.celada.domain.entity.Person;
import com.celada.domain.exception.PersonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class EventUseCase {

  private final PersonRepository personRepository;

  public void create(Person person) {
    log.info("Creating model: {}", person);
    personRepository.create(person);
  }

  public Person read(String key) throws PersonException {
    log.info("Reading model: {}", key);
    return personRepository.read(key);
  }

}
