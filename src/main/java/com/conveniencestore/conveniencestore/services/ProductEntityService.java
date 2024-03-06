package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.models.ProductEntity;
import com.conveniencestore.conveniencestore.domain.records.ProductEntityRecord;
import com.conveniencestore.conveniencestore.repositories.ProductEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductEntityService {
    private final ProductEntityRepository productEntityRepository;

    public ProductEntity insert(ProductEntityRecord request) {
        ProductEntity productEntity = new ProductEntity(request);
        productEntityRepository.save(productEntity);
        return productEntity;
    }

    //    public ProductEntity delete(){
//    }
//    public ProductEntity update(){
//    }
    public List<ProductEntity> getAll() {
        return productEntityRepository.findAll();
    }
//    public ProductEntity getById(int id){
//    }
}
