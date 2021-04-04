package com.digital.innovation.one.heroesapi.controller;

import com.digital.innovation.one.heroesapi.document.Heroes;
import com.digital.innovation.one.heroesapi.repository.HeroesRepository;
import com.digital.innovation.one.heroesapi.service.HeroesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static com.digital.innovation.one.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
public class HeroesController {
    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger log=
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public  HeroesController(HeroesService heroesService, HeroesRepository heroesRepository){
        this.heroesService=heroesService;
        this.heroesRepository=heroesRepository;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems(){
        log.info("requesting the list off all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) {
        log.info("Requesting the hero with id {}", id);
        return heroesService.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info("a new hero was created");
        return heroesService.save(heroes);
    }

    @PutMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<Heroes> updateHero(@Valid @RequestBody String hero){
        log.info("a new hero was updated");
        return heroesService.update(hero);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deleteByIdHero(@PathVariable String id) {
        heroesService.deleteByIdHero(id);
        log.info("Deleting the hero with id {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }

}
