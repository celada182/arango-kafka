package com.celada.adapter.out.arango;

import com.celada.adapter.out.arango.document.PersonEntity;
import com.celada.domain.PersonRepository;
import com.celada.domain.entity.Person;
import com.celada.domain.exception.PersonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class PersonRepositoryImpl implements PersonRepository {

  private PersonArangoRepository repository;

  @Override
  public void create(Person person) {
    PersonEntity entity = PersonMapper.execute(person);
    repository.save(entity);
    log.info("Saving person: {}", entity);
  }

  @Override
  public Person read(String name) throws PersonException {
    Optional<PersonEntity> entity = repository.findByName(name);
    PersonEntity person = entity
        .orElseThrow(() -> new PersonException("Person not found"));
    log.info("Read person: {}", name);
    return PersonMapper.execute(person);
  }

  @Override
  public void update(Person person) {

  }

  @Override
  public void delete(String id) {

  }
}
