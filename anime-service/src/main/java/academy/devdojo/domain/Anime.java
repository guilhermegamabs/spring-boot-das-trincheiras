package academy.devdojo.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Anime {
    private Long id;
    private String name;

    public Anime(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Anime> listAllAnimes() {
        return List.of(
                new Anime(1L, "Batman"),
                new Anime(2L, "Flash"),
                new Anime(3L, "Superman")
        );
    }
}
