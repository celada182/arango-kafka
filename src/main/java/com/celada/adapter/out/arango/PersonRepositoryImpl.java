package com.celada.adapter.out.arango;

import com.celada.adapter.out.arango.document.PersonEntity;
import com.celada.adapter.out.arango.edge.Friend;
import com.celada.adapter.out.arango.repository.BoyfriendArangoRepository;
import com.celada.adapter.out.arango.repository.FriendArangoRepository;
import com.celada.adapter.out.arango.repository.GirlfriendArangoRepository;
import com.celada.adapter.out.arango.repository.PersonArangoRepository;
import com.celada.adapter.out.arango.repository.RoommateArangoRepository;
import com.celada.domain.PersonRepository;
import com.celada.domain.entity.Person;
import com.celada.domain.exception.PersonException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@Slf4j
public class PersonRepositoryImpl implements PersonRepository {

  private final PersonArangoRepository people;
  private final FriendArangoRepository friends;
  private final BoyfriendArangoRepository boyfriends;
  private final GirlfriendArangoRepository girlfriends;
  private final RoommateArangoRepository roommates;

  @Override
  public void create(Person person) {
    PersonEntity entity = PersonMapper.execute(person);
    people.save(entity);
    Iterable<PersonEntity> personEntities = people.findAllById(person.getFriends());
    List<Friend> friendEntities = new ArrayList<>();
    personEntities.forEach(friend ->
        friendEntities.add(Friend.builder().to(entity).from(friend).build()));
    friends.saveAll(friendEntities);
    log.info("Saving person: {}", entity);
  }

  @Override
  public Person read(String key) throws PersonException {
    Optional<PersonEntity> entity = people.findByKey(key);
    PersonEntity person = entity
        .orElseThrow(() -> new PersonException("Person not found"));
    return PersonMapper.execute(person);
  }

  @Override
  public void update(Person person) {

  }

  @Override
  public void delete(String id) {

  }
}
