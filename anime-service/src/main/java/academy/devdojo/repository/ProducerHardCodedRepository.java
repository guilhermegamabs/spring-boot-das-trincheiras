package academy.devdojo.repository;

import academy.devdojo.domain.Producer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProducerHardCodedRepository {
    private static final List<Producer> PRODUCERS = new ArrayList<>();

    static {
            var producer1 = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).address("Rua 1").build();
            var producer2 = Producer.builder().id(2L).name("Kyoto").createdAt(LocalDateTime.now()).address("Rua 2").build();
            var producer3 = Producer.builder().id(3L).name("Madhouse").createdAt(LocalDateTime.now()).address("Rua 3").build();
            PRODUCERS.addAll(List.of(producer1, producer2, producer3));
    }

    public static List<Producer> findAll() {
        return PRODUCERS;
    }

    public Optional<Producer> findById(Long id) {
        return PRODUCERS.stream()
                        .filter(producer -> producer.getId().equals(id))
                        .findFirst();
    }

    public List<Producer> findByName(String name) {
        return PRODUCERS.stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
    }

    public Producer save(Producer producer) {
        PRODUCERS.add(producer);
        return producer;
    }

    public void delete(Producer producer) {
       PRODUCERS.remove(producer);
    }

    public void update(Producer producer) {
        delete(producer);
        save(producer);
    }
}
