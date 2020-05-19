package com.celada.adapter.in.rest;

import com.celada.domain.RestUseCase;
import com.celada.domain.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@AllArgsConstructor
public class PersonController {

  private RestUseCase restUseCase;

  @ResponseBody
  @PostMapping(value = "/person",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> create(@RequestBody Person person) {
    try {
      restUseCase.create(person);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
      log.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @ResponseBody
  @GetMapping(value = "/person/{key}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Person> read(@PathVariable String key) {
    try {
      Person result = restUseCase.read(key);
      return ResponseEntity.ok(result);
    } catch (ExecutionException | InterruptedException | IOException e) {
      log.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

}

