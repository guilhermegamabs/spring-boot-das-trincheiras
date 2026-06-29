package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class AnimeHardCodedRepository {
    private static final List<Anime> ANIMES = new ArrayList<>();

    static {
        var anime1 = Anime.builder().id(1L).name("Batman").build();
        var anime2 = Anime.builder().id(2L).name("Hulk").build();
        var anime3 = Anime.builder().id(3L).name("Superman").build();

        ANIMES.addAll(List.of(anime1, anime2, anime3));
    }

    public List<Anime> findAll() {
        return ANIMES;
    }

    public Optional<Anime> findById(Long id) {
        return ANIMES.stream().filter(anime -> anime.getId()
                .equals(id))
                .findFirst();
    }

    public List<Anime> findByName(String name) {
        return ANIMES.stream().filter(anime -> anime.getName()
                .equalsIgnoreCase(name))
                .toList();
    }

    public Anime save(Anime anime) {
        Long id = ThreadLocalRandom.current().nextLong(1, 1000);
        anime.setId(id);
        ANIMES.add(anime);

        return anime;

    }

    public void delete(Anime anime) {
        ANIMES.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        ANIMES.add(anime);
    }

}
