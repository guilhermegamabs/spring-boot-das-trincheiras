package academy.devdojo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RequestMapping("v1/greetings")
@RestController()
@Slf4j
public class HelloController {

    @GetMapping("hi")
    public String hi() {
        return "OMAE WA MOU SHINDE IRU";
    }

    @PostMapping
    public Long save(@RequestBody String name) {
        log.info("save {}", name);
        return ThreadLocalRandom.current().nextLong(1, 1000);
    }
}
