package academy.devdojo.controllers;

import academy.devdojo.domain.Producer;
import academy.devdojo.dto.ProducerPostRequest;
import academy.devdojo.response.ProducerGetResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequestMapping("v1/producers")
@RestController
@Slf4j
@Builder
public class ProducerController {

    @GetMapping("/nomeProducer")
    public List<Producer> listAll() {
        return Producer.getAllProducers();
    }

    @GetMapping("/filterList")
    public List<Producer> listAllFiltered(@RequestParam String name) {
       var producers = Producer.getAllProducers();
       if(name == null) return producers;

       return producers.stream()
               .filter(producer -> producer.getName().equalsIgnoreCase(name))
               .toList();
    }

    @GetMapping("{id}")
    public Producer findById(@PathVariable Long id) {
        return Producer.getAllProducers()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst().orElse(null);

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key")
    public ResponseEntity<ProducerGetResponse> create(@RequestBody(required = false) ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {
        log.info("{}", headers);

        var producer = Producer.builder()
                            .id(ThreadLocalRandom.current().nextLong(100_000))
                            .name(producerPostRequest.getName())
                            .createdAt(LocalDateTime.now())
                            .address(producerPostRequest.getAddress())
                            .build();

        Producer.addProducer(producer);

        var response = ProducerGetResponse.builder()
                            .id(producer.getId())
                            .name(producer.getName())
                            .createdAt(LocalDateTime.now())
                            .address(producerPostRequest.getAddress())
                            .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}