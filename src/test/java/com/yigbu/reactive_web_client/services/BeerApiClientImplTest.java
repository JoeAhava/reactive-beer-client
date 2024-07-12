package com.yigbu.reactive_web_client.services;

import com.yigbu.reactive_web_client.config.WebClientConfig;
import com.yigbu.reactive_web_client.dao.Beer;
import com.yigbu.reactive_web_client.dao.BeerStyleEnum;
import com.yigbu.reactive_web_client.dao.ListBeerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BeerApiClientImplTest {
    BeerApiClientImpl beerApiClient;

    @BeforeEach
    void setUp() {
        beerApiClient = new BeerApiClientImpl(new WebClientConfig().webClient());
    }

    @Test
    void list() {
        Mono<ListBeerResponse> listBeerResponseMono = beerApiClient.list(null,null,
                null,null,null);

        ListBeerResponse listBeerResponse = listBeerResponseMono.block();

        assertThat(listBeerResponse).isNotNull();
        assertThat(listBeerResponse.getContent().size()).isGreaterThan(0);
    }
    @Test
    void listPageSize9() {
        Mono<ListBeerResponse> listBeerResponseMono = beerApiClient.list(1,9,
                null,null,null);

        ListBeerResponse listBeerResponse = listBeerResponseMono.block();

        assertThat(listBeerResponse).isNotNull();
        assertThat(listBeerResponse.getContent().size()).isEqualTo(9);
    }

    @Test
    void listBeerStyle() {
        Mono<ListBeerResponse> listBeerResponseMono = beerApiClient.list(null,null,
                null, BeerStyleEnum.LAGER.name(),null);

        ListBeerResponse listBeerResponse = listBeerResponseMono.block();

        assertThat(listBeerResponse).isNotNull();
//        assertThat(listBeerResponse.getContent().get(0).getBeerStyle()).isEqualTo(BeerStyleEnum.LAGER.name());
        assertThat(listBeerResponse.getContent().stream().allMatch(beer -> beer.getBeerStyle() == BeerStyleEnum.LAGER.name()));
    }

    @Test
    void listNoRecords() {
        Mono<ListBeerResponse> listBeerResponseMono = beerApiClient.list(10,20,
                null, null,null);

        ListBeerResponse listBeerResponse = listBeerResponseMono.block();

        assertThat(listBeerResponse).isNotNull();
        assertThat(listBeerResponse.getContent().size()).isEqualTo(0);
    }

    @Test
    void getBeerById() {
        UUID uuid = UUID.fromString("66b01cb0-342e-47eb-9fe1-7e95cc74e0a9");
        Mono<Beer> beerMono = beerApiClient.getBeerById(uuid, true);

        Beer beer = beerMono.block();

        assertThat(beer).isNotNull();
        assertThat(beer.getId().toString()).isEqualTo(uuid.toString());
    }

    @Test
    void getBeerByUPC() {
        String upc = "0631234200036";
        Mono<Beer> beerMono = beerApiClient.getBeerByUPC(upc);

        Beer beer = beerMono.block();

        assertThat(beer).isNotNull();
        assertThat(beer.getUpc()).isEqualTo(upc);
    }

    @Test
    void deleteBeer() {
    }

    @Test
    void createBeer() {
        Beer beer = Beer.builder()
                .beerName("John Doe's Beer")
                .upc("564879798465321354487")
                .price(new BigDecimal("10.45"))
                .beerStyle("IPA")
                .build();

        Mono<ResponseEntity<Void>> responseEntityMono = beerApiClient.createBeer(beer);

        ResponseEntity responseEntity = responseEntityMono.block();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void updateBeer() {
        UUID uuid = UUID.fromString("7235ad9e-da13-47ec-921e-474e5bdf7853");
        Beer beer = Beer.builder()
                .beerName("John Doe's Beer")
                .beerStyle(BeerStyleEnum.LAGER.name())
                .upc("798451321312154978512")
                .price(new BigDecimal("10.45"))
                .build();

        Mono<ResponseEntity<Void>> responseEntityMono = beerApiClient.updateBeer(uuid, beer);

        ResponseEntity<Void> responseEntity = responseEntityMono.block();

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}