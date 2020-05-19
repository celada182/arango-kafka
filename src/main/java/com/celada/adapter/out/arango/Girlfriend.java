package com.celada.adapter.out.arango;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Builder;
import lombok.Data;

@Edge
@Data
@Builder
public class Girlfriend {

  @From
  private PersonEntity from;
  @To
  private PersonEntity to;

}
