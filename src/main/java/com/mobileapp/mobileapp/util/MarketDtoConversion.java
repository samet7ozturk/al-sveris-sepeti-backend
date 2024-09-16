package com.mobileapp.mobileapp.util;

import com.mobileapp.mobileapp.dto.MarketResponse;
import com.mobileapp.mobileapp.entity.Market;
import com.mobileapp.mobileapp.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MarketDtoConversion {

    public static List<MarketResponse> convertMarketList(List<Market> marketList) {
        return marketList.stream().map(market -> new MarketResponse(
                market.getId(),
                market.getName(),
                market.getLocation(),
                market.getProducts() != null ? market.getProducts().stream()
                        .map(Product::getName)
                        .collect(Collectors.toList()) : new ArrayList<>()
        )).collect(Collectors.toList());
    }
}
