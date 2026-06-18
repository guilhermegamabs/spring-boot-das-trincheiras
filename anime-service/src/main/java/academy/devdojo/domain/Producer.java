package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Producer {
    private Long id;
    @JsonProperty("name")
    private String name;

    private final static List<Producer> producerList = new ArrayList<>(List.of(
            new Producer(1L, "Mappa"),
            new Producer(2L, "Kyoto"),
            new Producer(3L, "Madhouse")
    ));

    public static void addProducer(Producer producer) {
        producerList.add(producer);
    }

    public static List<Producer> getAllProducers() {
        return producerList;
    }
}
