package com.mobileapp.mobileapp.service.impl;

import com.mobileapp.mobileapp.entity.Market;
import com.mobileapp.mobileapp.entity.Product;
import com.mobileapp.mobileapp.repository.MarketRepository;
import com.mobileapp.mobileapp.repository.ProductRepository;
import com.mobileapp.mobileapp.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;
    private final ProductRepository productRepository;  // ProductRepository'yi de ekledik

    @Autowired
    public MarketServiceImpl(MarketRepository marketRepository, ProductRepository productRepository) {
        this.marketRepository = marketRepository;
        this.productRepository = productRepository;  // Constructor'da productRepository'yi ekledik
    }

    @Override
    public Market save(Market market) {
        return marketRepository.save(market);
    }

    @Override
    public Market findById(Long id) {
        Optional<Market> market = marketRepository.findById(id);
        return market.orElseThrow(() -> new RuntimeException("Market with id " + id + " not found"));
    }

    @Override
    public List<Market> findAll() {
        return marketRepository.findAll();
    }

    @Override
    public List<Market> findByProductNameAndLocation(String productName, String location) {
        return marketRepository.findByProductsNameAndLocation(productName, location);
    }

    @Override
    public List<Market> findByProductName(String productName) {
        return marketRepository.findByProductsName(productName);
    }

    @Override
    public List<Market> findByLocation(String location) {
        return marketRepository.findByLocation(location);
    }

    @Override
    public void delete(Long id) {
        if (marketRepository.existsById(id)) {
            marketRepository.deleteById(id);
        } else {
            throw new RuntimeException("Market with id " + id + " not found");
        }
    }

    @Override
    public Market addProductToMarket(Long marketId, Product product) {
        Market market = marketRepository.findById(marketId)
                .orElseThrow(() -> new RuntimeException("Market with id " + marketId + " not found"));

        product.setMarket(market);
        productRepository.save(product);

        market.getProducts().add(product);
        return marketRepository.save(market);
    }
}
