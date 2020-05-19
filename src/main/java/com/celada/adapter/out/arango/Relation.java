package com.celada.adapter.out.arango;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class Relation {

  @From
  private Person child;
  @To
  private Person parent;

  private Type type;

}
