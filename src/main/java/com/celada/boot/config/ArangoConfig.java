package com.celada.boot.config;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import com.celada.adapter.out.arango.PersonRepositoryImpl;
import com.celada.adapter.out.arango.repository.BoyfriendArangoRepository;
import com.celada.adapter.out.arango.repository.FriendArangoRepository;
import com.celada.adapter.out.arango.repository.GirlfriendArangoRepository;
import com.celada.adapter.out.arango.repository.PersonArangoRepository;
import com.celada.adapter.out.arango.repository.RoommateArangoRepository;
import com.celada.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com.celada.adapter.out.arango"})
public class ArangoConfig extends AbstractArangoConfiguration {

  @Value("${spring.data.arangodb.host:localhost}")
  private String host;

  @Value("${spring.data.arangodb.port:8529}")
  private int port;

  @Value("${spring.data.arangodb.user}")
  private String user;

  @Value("${spring.data.arangodb.password}")
  private String password;

  @Value("${spring.data.arangodb.database}")
  private String database;

  @Override
  public Builder arango() {
    return new ArangoDB.Builder()
        .host(host, port)
        .user(user)
        .password(password);
  }

  @Override
  public String database() {
    return database;
  }

  @Bean
  public PersonRepository personRepository(
      PersonArangoRepository people,
      FriendArangoRepository friends,
      RoommateArangoRepository roomates,
      BoyfriendArangoRepository boyfriends,
      GirlfriendArangoRepository girlfriends
  ) {
    return PersonRepositoryImpl.builder()
        .people(people)
        .friends(friends)
        .roommates(roomates)
        .boyfriends(boyfriends)
        .girlfriends(girlfriends)
        .build();
  }
}
