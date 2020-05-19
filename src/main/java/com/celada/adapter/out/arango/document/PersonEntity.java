package com.celada.adapter.out.arango.document;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Key;
import com.arangodb.springframework.annotation.Relations;
import com.celada.adapter.out.arango.edge.Boyfriend;
import com.celada.adapter.out.arango.edge.Friend;
import com.celada.adapter.out.arango.edge.Girlfriend;
import com.celada.adapter.out.arango.edge.Roommate;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
@Document("person")
public class PersonEntity {

  @Key
  private String key;
  private String name;
  @Relations(edges = Friend.class, lazy = true)
  private Collection<PersonReducedEntity> friends;
  @Relations(edges = Roommate.class, lazy = true)
  private Collection<PersonReducedEntity> roommates;
  @Relations(edges = Girlfriend.class, lazy = true)
  private Collection<PersonReducedEntity> girlfriends;
  @Relations(edges = Boyfriend.class, lazy = true)
  private Collection<PersonReducedEntity> boyfriends;
}
