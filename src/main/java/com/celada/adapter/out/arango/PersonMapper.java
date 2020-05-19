package com.celada.adapter.out.arango;

import static java.util.stream.Collectors.toList;

import com.celada.domain.entity.Person;

import java.util.Collection;
import java.util.List;

public class PersonMapper {

  public static PersonEntity execute(Person person) {
    return PersonEntity.builder()
        .name(person.getName())
        .build();
  }

  public static Person execute(PersonEntity person) {
    return Person.builder()
        .name(person.getName())
        .boyfriends(execute(person.getBoyfriends()))
        .girlfriends(execute(person.getGirlfriends()))
        .friends(execute(person.getFriends()))
        .roommates(execute(person.getRoommates()))
        .build();
  }

  private static List<String> execute(Collection<PersonEntity> people) {
    return people.stream()
        .map(PersonEntity::getName)
        .collect(toList());
  }
}
