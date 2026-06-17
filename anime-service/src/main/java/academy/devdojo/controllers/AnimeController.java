package academy.devdojo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import academy.devdojo.domain.Anime;
import java.util.List;

@RequestMapping("v1/animes")
@RestController
@Slf4j
public class AnimeController {

    @GetMapping("/nomeAnimes")
    public List<Anime> listAll() {
        return Anime.getAllAnimes();
    }

    @GetMapping("/filterList")
    public List<Anime> listAllFiltered(@RequestParam String name) {
       var animes = Anime.getAllAnimes();
       if(name == null) return animes;

       return animes.stream()
               .filter(anime -> anime.getName().equalsIgnoreCase(name))
               .toList();
    }

    @GetMapping("{id}")
    public Anime findById(@PathVariable Long id) {
        return Anime.getAllAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst().orElse(null);

    }

    @PostMapping
    public Anime createAnime(@RequestBody(required = false) Anime anime) {
        return Anime.addAnime(anime.getName());
    }
}