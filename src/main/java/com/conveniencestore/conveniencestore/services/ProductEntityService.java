package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityDTO;
import com.conveniencestore.conveniencestore.repositories.ProductEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductEntityService {
    private final ProductEntityRepository productEntityRepository;

    public ProductEntity insert(ProductEntityDTO request) {
        ProductEntity productEntity = new ProductEntity(request);
        productEntityRepository.save(productEntity);
        return productEntity;
    }

    public ProductEntity delete(int id) {
        ProductEntity productEntity = this.productEntityRepository.findById(id).orElseThrow();
        this.productEntityRepository.delete(productEntity);
        return productEntity;
    }

    public ProductEntity update(int id, ProductEntityDTO productEntityRecord) {
        ProductEntity productEntity = this.productEntityRepository.findById(id).orElseThrow();
        productEntity.setName(productEntityRecord.name());
        productEntity.setUpdatedAt(LocalDateTime.now());
        this.productEntityRepository.save(productEntity);
        return productEntity;
    }

    public List<ProductEntity> getAll(String orderby) {
        return productEntityRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, orderby));
    }

    public ProductEntity getById(int id) {
        return this.productEntityRepository.findById(id).orElseThrow();
    }
}
