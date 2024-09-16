package com.mobileapp.mobileapp.controller;

import com.mobileapp.mobileapp.dto.MarketResponse;
import com.mobileapp.mobileapp.entity.Market;
import com.mobileapp.mobileapp.entity.Product;
import com.mobileapp.mobileapp.service.MarketService;
import com.mobileapp.mobileapp.util.MarketDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/markets")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:19000", "http://192.168.1.38:19000"})
public class MarketController {

    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping
    public ResponseEntity<List<MarketResponse>> getAllMarkets() {
        List<Market> markets = marketService.findAll();
        List<MarketResponse> marketResponses = MarketDtoConversion.convertMarketList(markets);
        return ResponseEntity.ok(marketResponses);
    }

    @PostMapping
    public ResponseEntity<MarketResponse> createMarket(@RequestBody Market market) {
        Market savedMarket = marketService.save(market);
        MarketResponse response = new MarketResponse(
                savedMarket.getId(),
                savedMarket.getName(),
                savedMarket.getLocation(),
                savedMarket.getProducts() != null ? savedMarket.getProducts().stream()
                        .map(Product::getName)
                        .collect(Collectors.toList()) : new ArrayList<>()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{marketId}/products")
    public ResponseEntity<MarketResponse> addProductToMarket(
            @PathVariable Long marketId,
            @RequestBody Product product
    ) {
        Market updatedMarket = marketService.addProductToMarket(marketId, product);
        MarketResponse response = new MarketResponse(
                updatedMarket.getId(),
                updatedMarket.getName(),
                updatedMarket.getLocation(),
                updatedMarket.getProducts() != null ? updatedMarket.getProducts().stream()
                        .map(Product::getName)
                        .collect(Collectors.toList()) : new ArrayList<>()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketResponse> getMarketById(@PathVariable Long id) {
        Market market = marketService.findById(id);
        MarketResponse response = new MarketResponse(
                market.getId(),
                market.getName(),
                market.getLocation(),
                market.getProducts() != null ? market.getProducts().stream()
                        .map(Product::getName)
                        .collect(Collectors.toList()) : new ArrayList<>()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MarketResponse>> searchMarketsByProductAndLocation(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String location) {

        List<Market> markets;

        if (productName != null && location != null) {
            markets = marketService.findByProductNameAndLocation(productName, location);
        } else if (productName != null) {
            markets = marketService.findByProductName(productName);
        } else if (location != null) {
            markets = marketService.findByLocation(location);
        } else {
            markets = marketService.findAll();
        }

        List<MarketResponse> marketResponses = MarketDtoConversion.convertMarketList(markets);
        return ResponseEntity.ok(marketResponses);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id) {
        marketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
