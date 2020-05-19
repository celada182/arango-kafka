package com.celada.adapter.out.arango;

import com.arangodb.springframework.annotation.Key;
import com.arangodb.springframework.annotation.Relations;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class Person {

  @Key
  private String key;
  private String name;
  @Relations(edges = Friend.class, lazy = true)
  private Collection<Person> friends;
  @Relations(edges = Roommate.class, lazy = true)
  private Collection<Person> roommates;
  @Relations(edges = Girlfriend.class, lazy = true)
  private Collection<Person> girlfriends;
  @Relations(edges = Boyfriend.class, lazy = true)
  private Collection<Person> boyfriends;
}
