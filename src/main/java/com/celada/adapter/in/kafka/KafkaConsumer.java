package com.celada.adapter.in.kafka;

import com.celada.domain.EventUseCase;
import com.celada.domain.entity.Person;
import com.celada.domain.exception.PersonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {

  private final EventUseCase eventUseCase;
  private final EventMapper eventMapper;

  @KafkaListener(topics = "${kafka.topic.request}")
  @SendTo
  public Event listen(Event event)
      throws IOException, PersonException {
    log.info("Consumer | Event received: {}", event);
    if (EventType.CREATE.equals(event.getType())) {
      create(event);
    }
    if (EventType.READ.equals(event.getType())) {
      read(event);
    }
    return event;
  }

  private void read(Event event) throws IOException, PersonException {
    Person request = eventMapper.execute(event.getBody());
    Person person = eventUseCase.read(request.getName());
    event.setBody(eventMapper.execute(person));
    log.info("Sending event: {}", event);
  }

  private void create(Event event) throws IOException {
    Person person = eventMapper.execute(event.getBody());
    eventUseCase.create(person);
    log.info("Sending event: {}", event);
  }

}
