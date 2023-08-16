package com.example.jpa1.product;

import com.example.jpa1.entity.ProductEntity;
import com.example.jpa1.product.model.ProductUpdDto;
import com.example.jpa1.product.model.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductVo> getProductAll() {
        return service.getProductAll();
    }

    @PutMapping
    public ProductEntity putProduct(@RequestBody ProductUpdDto dto) {
        return service.updProduct(dto);
    }
}
