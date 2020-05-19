package com.celada.adapter.out.arango.document;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Key;
import com.arangodb.springframework.annotation.Relations;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
@Document("person")
public class PersonReducedEntity {

  @Key
  private String key;
  private String name;
}
