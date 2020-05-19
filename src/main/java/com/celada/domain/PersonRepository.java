package com.celada.domain;

import com.celada.domain.entity.Person;
import com.celada.domain.exception.PersonException;

public interface PersonRepository {

  void create(Person person);

  Person read(String key) throws PersonException;

  void update(Person person);

  void delete(String id);

}
