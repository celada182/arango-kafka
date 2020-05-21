package com.celada.adapter.out.arango;

import com.celada.adapter.out.arango.document.PersonEntity;
import com.celada.adapter.out.arango.edge.Boyfriend;
import com.celada.adapter.out.arango.edge.Friend;
import com.celada.adapter.out.arango.edge.Girlfriend;
import com.celada.adapter.out.arango.edge.Roommate;
import com.celada.adapter.out.arango.repository.BoyfriendArangoRepository;
import com.celada.adapter.out.arango.repository.FriendArangoRepository;
import com.celada.adapter.out.arango.repository.GirlfriendArangoRepository;
import com.celada.adapter.out.arango.repository.PersonArangoRepository;
import com.celada.adapter.out.arango.repository.RoommateArangoRepository;
import com.celada.domain.PersonRepository;
import com.celada.domain.entity.Person;
import com.celada.domain.exception.PersonException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

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
    List<Friend> friendEntities = PersonMapper.friends(person);
    friends.saveAll(friendEntities);
    List<Boyfriend> boyfriendsEntities = PersonMapper.boyfriends(person);
    boyfriends.saveAll(boyfriendsEntities);
    List<Girlfriend> girlfriendsEntities = PersonMapper.girlfriends(person);
    girlfriends.saveAll(girlfriendsEntities);
    List<Roommate> roommatesEntities = PersonMapper.roommates(person);
    roommates.saveAll(roommatesEntities);
    log.info("Saving person: {}", entity);
  }

  @Override
  public Person read(String key) throws PersonException {
    Optional<PersonEntity> entity = people.findByKey(key);
    PersonEntity person = entity
        .orElseThrow(() -> new PersonException("Person not found"));
    log.info("Read person: {}", entity);
    return PersonMapper.execute(person);
  }

  @Override
  public void update(Person person) {

  }

  @Override
  public void delete(String id) {

  }
}
