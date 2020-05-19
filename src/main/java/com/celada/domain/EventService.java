package com.celada.domain;

import com.celada.domain.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface EventService {

  void create(Person person) throws ExecutionException, InterruptedException, JsonProcessingException;

  Person read(String id) throws ExecutionException, InterruptedException, IOException;

  void update(Person model);

  void delete(String id);

}
