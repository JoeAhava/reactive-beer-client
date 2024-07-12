package com.yigbu.reactive_web_client.services;

import com.yigbu.reactive_web_client.config.WebClientConfig;
import com.yigbu.reactive_web_client.dao.BeerStyleEnum;
import com.yigbu.reactive_web_client.dao.ListBeerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void getBeerByUPC() {
    }

    @Test
    void deleteBeer() {
    }

    @Test
    void createBeer() {
    }

    @Test
    void updateBeer() {
    }
}