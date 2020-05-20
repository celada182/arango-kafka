# Arango DB - Kafka
Spring Boot - Arango DB - Apache Kafka - Application

### Docker

* Arango DB 

    `docker run --name arango -p 8529:8529 -e ARANGO_ROOT_PASSWORD=openSesame arangodb/arangodb:3.6.3.1`
* Kafka 

    `docker run --rm -p 2181:2181 -p 3030:3030 -p 8081-8083:8081-8083 -p 9581-9585:9581-9585 -p 9092:9092 -e ADV_HOST=127.0.0.1 landoop/fast-data-dev:latest`
* Topics UI 
    
    `docker run --rm -it -p 8000:8000 -e "KAFKA_REST_PROXY_URL=http://localhost:8082"  landoop/kafka-topics-ui`

### Arango DB

    http://localhost:8529/ => root/openSesame
    
### Swagger

    http://localhost:8071 (server.port)
    
### Application

    Run `com.celada.boot.Application`
