package com.celada.adapter.out.arango;

import com.arangodb.springframework.repository.ArangoRepository;
import com.celada.adapter.out.arango.document.PersonEntity;

import java.util.Optional;

public interface PersonArangoRepository extends ArangoRepository<PersonEntity> {

  Optional<PersonEntity> findByKey(String key);
}
