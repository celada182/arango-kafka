package com.celada.adapter.out.arango;

import com.arangodb.springframework.annotation.Key;
import com.arangodb.springframework.annotation.Relations;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class PersonEntity {

  @Key
  private String key;
  private String name;
  @Relations(edges = Friend.class, lazy = true)
  private Collection<PersonEntity> friends;
  @Relations(edges = Roommate.class, lazy = true)
  private Collection<PersonEntity> roommates;
  @Relations(edges = Girlfriend.class, lazy = true)
  private Collection<PersonEntity> girlfriends;
  @Relations(edges = Boyfriend.class, lazy = true)
  private Collection<PersonEntity> boyfriends;
}
