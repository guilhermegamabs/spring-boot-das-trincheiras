package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producer {
    @EqualsAndHashCode.Include
    @Setter
    private Long id;
    @JsonProperty("name")
    private String name;
    @Setter
    private LocalDateTime createdAt;
    private String address;
}
