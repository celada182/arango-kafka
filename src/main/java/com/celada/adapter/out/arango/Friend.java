package com.celada.adapter.out.arango;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Builder;
import lombok.Data;

@Edge
@Data
@Builder
public class Friend {

  @From
  private Person from;
  @To
  private Person to;

}
