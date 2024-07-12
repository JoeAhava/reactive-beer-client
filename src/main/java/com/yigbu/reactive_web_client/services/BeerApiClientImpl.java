package com.yigbu.reactive_web_client.services;

import com.yigbu.reactive_web_client.config.WebClientProperties;
import com.yigbu.reactive_web_client.dao.Beer;
import com.yigbu.reactive_web_client.dao.ListBeerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerApiClientImpl implements BeerApiClient {
    private final WebClient webClient;
    @Override
    public Mono<ListBeerResponse> list(Integer pageNumber, Integer pageSize, String beerName,
                                       String beerStyle, Boolean showInventoryOnhand) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(WebClientProperties.BEER_PATH_V1)
                        .queryParamIfPresent("pageNumber", Optional.ofNullable(pageNumber))
                        .queryParamIfPresent("pageSize", Optional.ofNullable(pageSize))
                        .queryParamIfPresent("beerName", Optional.ofNullable(beerName))
                        .queryParamIfPresent("beerStyle", Optional.ofNullable(beerStyle))
                        .queryParamIfPresent("showInventoryOnHand", Optional.ofNullable(showInventoryOnhand))
                        .build()
                )
                .retrieve()
                .bodyToMono(ListBeerResponse.class);
    }

    @Override
    public Mono<Beer> getBeerById(UUID id, Boolean showInventoryOnHand) {
        return null;
    }

    @Override
    public Mono<Beer> getBeerByUPC(String upc) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> deleteBeer(UUID id) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> createBeer(Beer beer) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> updateBeer(Beer beer) {
        return null;
    }
}
