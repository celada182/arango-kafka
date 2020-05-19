package com.celada.domain;

import com.celada.domain.entity.Person;
import com.celada.domain.exception.PersonException;

public interface PersonRepository {

  void create(Person person);

  Person read(String id) throws PersonException;

  void update(Person person);

  void delete(String id);

}
