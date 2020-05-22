package com.celada.adapter.out.arango.document;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Key;
import com.arangodb.springframework.annotation.Relations;
import com.arangodb.springframework.annotation.Relations.Direction;
import com.celada.adapter.out.arango.edge.Friend;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;

@Data
@ToString(exclude = "friends")
@Builder
@Document("person")
public class PersonEntity {

  @Key
  private String key;
  private String name;
  @Relations(edges = Friend.class, lazy = true, direction = Direction.OUTBOUND)
  private Collection<PersonEntity> friends;
}
