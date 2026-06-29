package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerData {
    private final List<Producer> producers = new ArrayList<>();

    {
        var producer1 = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).address("Rua 1").build();
        var producer2 = Producer.builder().id(2L).name("Kyoto").createdAt(LocalDateTime.now()).address("Rua 2").build();
        var producer3 = Producer.builder().id(3L).name("Madhouse").createdAt(LocalDateTime.now()).address("Rua 3").build();
        producers.addAll(List.of(producer1, producer2, producer3));
    }

    public List<Producer> getProducers() {
        return producers;
    }
}
