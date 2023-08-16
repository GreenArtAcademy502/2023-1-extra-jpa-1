package com.example.jpa1.entity;

import com.example.jpa1.repository.ProductRepository;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductEntityTest {

    @Autowired
    private ProductRepository productRep;

    @Test
    public void insProduct() {
        ProductEntity productEntity = ProductEntity.builder()
                .name("솜사탕")
                .price(1_000)
                .stock(25)
                .build();

        productRep.save(productEntity);
    }

    @Test
    @Transactional
    public void updProduct() {
        ProductEntity productEntity = productRep.getReferenceById(1L);

        log.info("productEntity : {}", productEntity);

        productEntity.setName("곰사탕");
        productEntity.setPrice(2000);

        productRep.save(productEntity);
    }
}