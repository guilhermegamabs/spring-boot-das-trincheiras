package academy.devdojo.controllers;

import academy.devdojo.domain.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequestMapping("v1/producers")
@RestController
@Slf4j
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
    public ResponseEntity<Producer> create(@RequestBody(required = false) Producer producer, @RequestHeader HttpHeaders headers) {
        log.info("{}", headers);
        Long id = ThreadLocalRandom.current().nextLong(1, 1000);
        Producer producer1 = new Producer(id, producer.getName());
        Producer.addProducer(producer1);
        return ResponseEntity.status(HttpStatus.CREATED).body(producer1);
    }
}