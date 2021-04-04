package com.digital.innovation.one.heroesapi.service;

import com.digital.innovation.one.heroesapi.document.Heroes;
import com.digital.innovation.one.heroesapi.repository.HeroesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class HeroesService {
    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository){

        this.heroesRepository=heroesRepository;
    }

    public Flux<Heroes> findAll(){

        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findByIdHero(String id){

        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> save(Heroes heroes){

        return Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono<Heroes> update(String id) {
        Optional<Heroes> hero = heroesRepository.findById(id);
        if(hero.isEmpty()){
            throw new RuntimeException("There is no hero with this id");
        }
        BeanUtils.copyProperties(heroesRepository, hero.get(), "id");

        return Mono.justOrEmpty(heroesRepository.save(hero.get()));
    }

    public Mono<Boolean> deleteByIdHero(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
