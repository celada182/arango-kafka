package com.celada.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  private String key;
  private String name;
  private List<String> friends;
  private List<String> roommates;
  private List<String> girlfriends;
  private List<String> boyfriends;
}
