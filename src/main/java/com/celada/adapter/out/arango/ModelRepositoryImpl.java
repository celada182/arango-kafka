package com.celada.adapter.out.arango;

import com.celada.domain.ModelRepository;
import com.celada.domain.entity.Model;
import com.celada.domain.exception.ModelException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ModelRepositoryImpl implements ModelRepository {

  @Override
  public void create(Model model) {
    log.info("Saving model:");
  }

  @Override
  public Model read(String id) throws ModelException {
    log.info("Read model:");
    return null;
  }

  @Override
  public void update(Model model) {

  }

  @Override
  public void delete(String id) {

  }
}
