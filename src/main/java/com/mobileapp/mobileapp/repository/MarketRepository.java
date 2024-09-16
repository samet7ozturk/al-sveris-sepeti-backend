package com.mobileapp.mobileapp.repository;

import com.mobileapp.mobileapp.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

    // Ürün adına ve lokasyona göre arama
    List<Market> findByProductsNameAndLocation(String productName, String location);

    // Sadece ürün adına göre arama
    List<Market> findByProductsName(String productName);

    // Sadece lokasyona göre arama
    List<Market> findByLocation(String location);
}
