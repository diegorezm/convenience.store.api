package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityDTO;
import com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions.ProductEntityNotFoundException;
import com.conveniencestore.conveniencestore.repositories.ProductEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductEntityService  implements ServiceInterface<ProductEntity, ProductEntityDTO>{
    private final ProductEntityRepository productEntityRepository;

    public ProductEntity insert(ProductEntityDTO request) {
        ProductEntity productEntity = new ProductEntity(request);
        productEntity = productEntityRepository.save(productEntity);
        return productEntity;
    }

    public ProductEntity delete(int id) {
        ProductEntity productEntity = this.productEntityRepository.findById(id).orElseThrow(ProductEntityNotFoundException::new);
        this.productEntityRepository.delete(productEntity);
        return productEntity;
    }

    public ProductEntity update(int id, ProductEntityDTO productEntityRecord) {
        ProductEntity productEntity = this.productEntityRepository.findById(id).orElseThrow(ProductEntityNotFoundException::new);
        productEntity.setName(productEntityRecord.name());
        productEntity.setUpdatedAt(LocalDateTime.now());
        productEntity = this.productEntityRepository.save(productEntity);
        return productEntity;
    }

    public List<ProductEntity> getAll(String orderby, String order) {
        Sort.Direction direction;
        switch (order){
            case "asc" -> {
                direction = Sort.Direction.ASC;
            }
            case "desc" -> {
                direction = Sort.Direction.DESC;
            }
            default -> {
                direction = Sort.DEFAULT_DIRECTION;
            }
        }
        return productEntityRepository.findAll(Sort.by(direction, orderby));
    }

    public ProductEntity getById(int id) {
        return this.productEntityRepository.findById(id).orElseThrow(ProductEntityNotFoundException::new);
    }
}
