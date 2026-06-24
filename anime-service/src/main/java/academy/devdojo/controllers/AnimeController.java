package academy.devdojo.controllers;

import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import academy.devdojo.domain.Anime;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("v1/animes")
@RestController
@Slf4j
public class AnimeController {
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping("/nomeAnimes")
    public List<Anime> listAll() {
        return Anime.getAllAnimes();
    }

    @GetMapping("/filterList")
    public ResponseEntity<List<AnimeGetResponse>> listAllFiltered(@RequestParam String name) {
        log.debug("Request to get  all Animes param name: {}", name);
        var animes = Anime.getAllAnimes();

        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);
        if(name == null) return ResponseEntity.ok(animeGetResponseList);

        var response = animeGetResponseList.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {

        log.debug("Request to get Anime : {}", id);
        var animeGetResponse =  Anime.getAllAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .map(MAPPER::toAnimeGetResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> createAnime(@RequestBody(required = false) AnimePostRequest request) {
        log.debug("Request to create Anime : {}", request);
        var anime = MAPPER.toAnime(request);
        Anime.addAnime(anime.getName());
        var response = MAPPER.toAnimePostResponse(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        log.debug("Request to delete Anime : {}", id);

        var animeToDelete = Anime.getAllAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        Anime.getAllAnimes().remove(animeToDelete);
        return ResponseEntity.noContent().build();
    }
}