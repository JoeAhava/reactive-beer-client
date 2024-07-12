package com.yigbu.reactive_web_client.services;

import com.yigbu.reactive_web_client.dao.Beer;
import com.yigbu.reactive_web_client.dao.ListBeerResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BeerApiClient {
    Mono<ListBeerResponse> list(Integer pageNumber, Integer pageSize, String beerName,
                                String beerStyle, Boolean showInventoryOnhand);
    Mono<Beer> getBeerById(UUID id, Boolean showInventoryOnHand);
    Mono<Beer> getBeerByUPC(String upc);
    Mono<ResponseEntity> deleteBeer(UUID id);
    Mono<ResponseEntity<Void>> createBeer(Beer beer);
    Mono<ResponseEntity<Void>> updateBeer(UUID beerId, Beer request);
}

/*
    Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand);

    Mono<BeerPagedList> listBeers(Integer pageNumber, Integer pageSize, String beerName,
                                  String beerStyle, Boolean showInventoryOnhand);

    Mono<ResponseEntity> createBeer(BeerDto beerDto);

    Mono<ResponseEntity> updateBeer(BeerDto beerDto);

    Mono<ResponseEntity> deleteBeerById(UUID id);

    Mono<BeerDto> getBeerByUPC(String upc);
 */