package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@AllArgsConstructor
public class Anime {
    private Long id;
    private String name;

    private final static List<Anime> animeList = new ArrayList<>(List.of(
            new Anime(1L, "Batman"),
            new Anime(2L, "Flash"),
            new Anime(3L, "Superman")
    ));

    public static Anime addAnime(String nome) {
        Long id = ThreadLocalRandom.current().nextLong(1, 1000);

        animeList.add(new Anime(id, nome));
        return new Anime(id, nome);

    }

    public static List<Anime> getAllAnimes() {
        return animeList;
    }
}
