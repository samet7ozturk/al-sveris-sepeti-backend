package com.mobileapp.mobileapp.service;

import com.mobileapp.mobileapp.entity.Market;
import com.mobileapp.mobileapp.entity.Product;

import java.util.List;

public interface MarketService {

    Market save(Market market);

    Market addProductToMarket(Long marketId, Product product);

    Market findById(Long id);

    List<Market> findAll();

    void delete(Long id);

    List<Market> findByProductNameAndLocation(String productName, String location);

    List<Market> findByProductName(String productName);

    List<Market> findByLocation(String location);
}
