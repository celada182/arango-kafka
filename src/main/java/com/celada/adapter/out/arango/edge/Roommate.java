package com.celada.adapter.out.arango.edge;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.celada.adapter.out.arango.document.PersonEntity;
import lombok.Builder;
import lombok.Data;

@Edge
@Data
@Builder
public class Roommate {

  @From
  private PersonEntity from;
  @To
  private PersonEntity to;

}
