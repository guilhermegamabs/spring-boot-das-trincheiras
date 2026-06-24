package academy.devdojo.controllers;

import academy.devdojo.domain.Producer;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.response.ProducerGetResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("v1/producers")
@RestController
@Slf4j
@Builder
public class ProducerController {
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping("/nomeProducer")
    public List<Producer> listAll() {
        return Producer.getAllProducers();
    }

    @GetMapping("/filterList")
    public ResponseEntity<List<Producer>> listAllFiltered(@RequestParam String name) {
       var producers = Producer.getAllProducers();
       if(name == null) return ResponseEntity.ok(producers);

       var response = producers.stream()
               .filter(producer -> producer.getName().equalsIgnoreCase(name))
               .toList();

       return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        var response =  Producer.getAllProducers()
                    .stream()
                    .filter(producer -> producer.getId().equals(id))
                    .findFirst()
                    .map(MAPPER::toProducerGetResponse)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));

        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key")
    public ResponseEntity<ProducerGetResponse> create(@RequestBody(required = false) ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {
        log.info("{}", headers);
        var producer = MAPPER.toProducer(producerPostRequest);
        var response = MAPPER.toProducerGetResponse(producer);

        Producer.addProducer(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete Producer : {}", id);
        var producerToDelete = Producer.getAllProducers()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));

        Producer.getAllProducers().remove(producerToDelete);
        return ResponseEntity.noContent().build();
    }

}