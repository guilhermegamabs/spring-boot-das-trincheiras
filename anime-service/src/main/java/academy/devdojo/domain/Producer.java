package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Producer {
    @Setter
    private Long id;
    @JsonProperty("name")
    private String name;
    private LocalDateTime createdAt;
    private String address;

    private final static List<Producer> producerList = new ArrayList<>(List.of(
            Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).address("Rua 1").build(),
            Producer.builder().id(2L).name("Kyoto").createdAt(LocalDateTime.now()).address("Rua 2").build(),
            Producer.builder().id(3L).name("Madhouse").createdAt(LocalDateTime.now()).address("Rua 3").build()
    ));

    public static void addProducer(Producer producer) {
        producerList.add(producer);
    }

    public static List<Producer> getAllProducers() {
        return producerList;
    }

}
