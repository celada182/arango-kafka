package com.celada.adapter.out.kafka;

import com.celada.adapter.in.kafka.Event;
import com.celada.adapter.in.kafka.EventType;
import com.celada.domain.EventService;
import com.celada.domain.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Slf4j
public class KafkaService implements EventService {

  private final ReplyingKafkaTemplate<String, Event, Event> kafkaTemplate;
  private final ObjectMapper objectMapper;

  private final String requestTopic;
  private final String responseTopic;

  @Override
  public void create(Person person) throws ExecutionException, InterruptedException, JsonProcessingException {
    Event request = Event.builder()
        .type(EventType.CREATE)
        .body(objectMapper.writeValueAsString(person))
        .build();
    sendEvent(request);
  }

  @Override
  public Person read(String key) throws ExecutionException, InterruptedException, IOException {
    Person person = Person.builder().key(key).build();
    Event request = Event.builder()
        .type(EventType.READ)
        .body(objectMapper.writeValueAsString(person))
        .build();
    ConsumerRecord<String, Event> response = sendEvent(request);
    return objectMapper.readValue(response.value().getBody(), Person.class);
  }

  @Override
  public void update(Person person) {
  }

  @Override
  public void delete(String id) {
  }

  private ConsumerRecord<String, Event> sendEvent(Event request) throws ExecutionException, InterruptedException {
    // create producer record
    ProducerRecord<String, Event> record = new ProducerRecord<>(requestTopic, request);
    // set reply topic in header
    RecordHeader replyHeader = new RecordHeader(KafkaHeaders.REPLY_TOPIC, responseTopic.getBytes());
    record.headers().add(replyHeader);
    // post in kafka topic
    RequestReplyFuture<String, Event, Event> sendAndReceive = kafkaTemplate.sendAndReceive(record);

    // confirm if producer produced successfully
    SendResult<String, Event> sendResult = sendAndReceive.getSendFuture().get();
    log.info("Model request sent: {}", request);

    //print all headers
    sendResult.getProducerRecord()
        .headers()
        .forEach(header -> log.info(header.key() + ":" + Arrays.toString(header.value())));

    // get consumer record
    return sendAndReceive.get();
  }
}
