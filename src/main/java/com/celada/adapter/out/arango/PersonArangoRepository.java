package com.celada.adapter.out.arango;

import com.arangodb.springframework.repository.ArangoRepository;

import java.util.Optional;

public interface PersonArangoRepository extends ArangoRepository<PersonEntity> {

  Optional<PersonEntity> findByName(String name);
}
