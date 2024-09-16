package com.mobileapp.mobileapp.util;

import com.mobileapp.mobileapp.dto.ProductResponse;
import com.mobileapp.mobileapp.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDtoConversion {

    public static List<ProductResponse> convertProductList(List<Product> productList){
        List<ProductResponse> productResponseList = productList.stream().map(product -> new ProductResponse(
                product.getId()
                , product.getName())
        ).collect(Collectors.toList());
        return productResponseList;
    }
}
