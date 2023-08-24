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
    public void updProduct() {
        //ProductEntity productEntity = productRep.getReferenceById(2L); //지연로딩
        ProductEntity productEntity = productRep.findById(2L).get(); //즉시로딩

        log.info("productEntity : {}", productEntity);

        productEntity.setName("꿈사탕");
        productEntity.setPrice(3000);

        //TODO: 수정 안 되는 이유를 찾아야 함 >> findById를 사용하여 해결, @Transactional 도 필요없음
        productRep.saveAndFlush(productEntity);
    }
}