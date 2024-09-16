package com.mobileapp.mobileapp.dto;

import java.util.List;

public record MarketResponse(Long id, String name, String location, List<String> products) {
}
