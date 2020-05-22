package com.celada.adapter.out.arango;

import static java.util.stream.Collectors.toList;

import com.celada.adapter.out.arango.document.PersonEntity;
import com.celada.adapter.out.arango.edge.Boyfriend;
import com.celada.adapter.out.arango.edge.Friend;
import com.celada.adapter.out.arango.edge.Girlfriend;
import com.celada.adapter.out.arango.edge.Roommate;
import com.celada.domain.entity.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PersonMapper {

  public static PersonEntity execute(Person person) {
    return PersonEntity.builder()
        .key(person.getKey())
        .name(person.getName())
        .build();
  }

  public static Person execute(PersonEntity person) {
    return Person.builder()
        .name(person.getName())
        .friends(execute(person.getFriends()))
        .build();
  }

  private static List<String> execute(Collection<PersonEntity> people) {
    return people.stream()
        .map(PersonEntity::getName)
        .collect(toList());
  }

  public static List<Friend> friends(Person person) {
    if (person == null || person.getKey() == null || person.getFriends() == null) {
      return Collections.emptyList();
    }
    return person.getFriends().stream()
        .map(f -> friend(person.getKey(), f))
        .flatMap(Collection::stream)
        .collect(toList());
  }

  private static List<Friend> friend(String key, String friend) {
    PersonEntity person = PersonEntity.builder().key(key).build();
    PersonEntity relation = PersonEntity.builder().key(friend).build();
    return Arrays.asList(
        Friend.builder().from(person).to(relation).build(),
        Friend.builder().from(relation).to(person).build());
  }

  public static List<Roommate> roommates(Person person) {
    if (person == null || person.getKey() == null || person.getRoommates() == null) {
      return Collections.emptyList();
    }
    return person.getRoommates().stream()
        .map(f -> roommate(person.getKey(), f))
        .flatMap(Collection::stream)
        .collect(toList());
  }

  private static List<Roommate> roommate(String key, String roommate) {
    PersonEntity person = PersonEntity.builder().key(key).build();
    PersonEntity relation = PersonEntity.builder().key(roommate).build();
    return Arrays.asList(
        Roommate.builder().from(person).to(relation).build(),
        Roommate.builder().from(relation).to(person).build());
  }

  public static List<Girlfriend> girlfriends(Person person) {
    if (person == null || person.getKey() == null || person.getGirlfriends() == null) {
      return Collections.emptyList();
    }
    return person.getGirlfriends().stream()
        .map(f -> girlfriend(person.getKey(), f))
        .flatMap(Collection::stream)
        .collect(toList());
  }

  private static List<Girlfriend> girlfriend(String key, String girlfriend) {
    PersonEntity person = PersonEntity.builder().key(key).build();
    PersonEntity relation = PersonEntity.builder().key(girlfriend).build();
    return Collections.singletonList(
        Girlfriend.builder().from(person).to(relation).build());
  }

  public static List<Boyfriend> boyfriends(Person person) {
    if (person == null || person.getKey() == null || person.getBoyfriends() == null) {
      return Collections.emptyList();
    }
    return person.getBoyfriends().stream()
        .map(f -> boyfriend(person.getKey(), f))
        .flatMap(Collection::stream)
        .collect(toList());
  }

  private static List<Boyfriend> boyfriend(String key, String boyfriend) {
    PersonEntity person = PersonEntity.builder().key(key).build();
    PersonEntity relation = PersonEntity.builder().key(boyfriend).build();
    return Collections.singletonList(
        Boyfriend.builder().from(person).to(relation).build());
  }
}
