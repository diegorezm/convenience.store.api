package com.conveniencestore.conveniencestore.components;

import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityDTO;
import com.conveniencestore.conveniencestore.services.ProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyProductEntitiesData implements CommandLineRunner {
    private final ProductEntityService service;
    private final String[] productNames = {"Milk", "Bread", "Eggs", "Apples", "Bananas", "Orange Juice",
            "Coffee", "Tea", "Cereal", "Pasta", "Tomato Sauce", "Cheese",
            "Yogurt", "Water", "Soda", "Chips", "Candy", "Cookies",
            "Ice Cream", "Paper Towels"};
    List<ProductEntityDTO> productList = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        for (String productName: this.productNames){
            this.productList.add(new ProductEntityDTO(productName));
        }
        for (ProductEntityDTO record : productList){
            this.service.insert(record);
        }
    }
}
