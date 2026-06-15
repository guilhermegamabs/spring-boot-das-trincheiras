package academy.devdojo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("v1/animes")
@RestController
@Slf4j
public class AnimeController {
    private final List<String> animes = new ArrayList<>();

    public AnimeController(){
        animes.add("Solo Leveling");
        animes.add("Ninja Kamui");
    }

    @GetMapping("/nomeAnimes")
    public List<String> listAll() {
        log.info(Thread.currentThread().getName());
        return animes;
    }
}
