package com.example.jpa1.product;

import com.example.jpa1.entity.ProductEntity;
import com.example.jpa1.product.model.ProductUpdDto;
import com.example.jpa1.product.model.ProductVo;
import com.example.jpa1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRep;

    public List<ProductVo> getProductAll() {
        List<ProductEntity> list = productRep.findAll();


        List<ProductVo> resultList = new ArrayList();


        return resultList;
    }

    public ProductEntity updProduct(ProductUpdDto dto) {
        ProductEntity result = productRep.getReferenceById(dto.getNumber());
        result.setPrice(dto.getPrice());
        result.setName(dto.getName());
        result.setStock(dto.getStock());

        return productRep.save(result);
    }
}
