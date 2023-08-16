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
    public List<ProductVo> getProductAll(@RequestParam(required = false) String name) {
        return service.getProductAll(name);
    }

    @GetMapping("/{number}")
    public ProductVo getProduct(@PathVariable long number) {
        return service.getProduct(number);
    }

    @GetMapping("/search")
    public List<ProductVo> getProductAllSearch(@RequestParam String name) {
        return service.getProductAllSearch(name);
    }


    @PutMapping
    public ProductEntity putProduct(@RequestBody ProductUpdDto dto) {
        return service.updProduct(dto);
    }

    @DeleteMapping("/{number}")
    public int delProduct(@PathVariable long number) {
        return service.delProduct(number);
    }
}
