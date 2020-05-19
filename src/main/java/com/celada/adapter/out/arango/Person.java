package com.celada.adapter.out.arango;

import com.arangodb.springframework.annotation.Key;
import com.arangodb.springframework.annotation.Relations;

import java.util.Collection;

public abstract class Person {

  @Key
  private String key;
  private String name;
  @Relations(edges = Relation.class, lazy = true)
  private Collection<Person> friends;
  @Relations(edges = Relation.class, lazy = true)
  private Collection<Person> roomates;
  @Relations(edges = Relation.class, lazy = true)
  private Collection<Person> girlfriend;
}
