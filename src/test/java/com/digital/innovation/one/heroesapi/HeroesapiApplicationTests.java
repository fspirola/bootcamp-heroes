package com.digital.innovation.one.heroesapi;

import com.digital.innovation.one.heroesapi.constants.HeroesConstant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.digital.innovation.one.heroesapi.document.Heroes;
import com.digital.innovation.one.heroesapi.repository.HeroesRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.digital.innovation.one.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesapiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	HeroesConstant heroesRepository;

	@Test
	public void getOneHeroById(){
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), 1)
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getOneHeroNotFound(){
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void deleteHero(){
		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "5")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);

	}

}
