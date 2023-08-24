package com.example.jpa1.product;

import com.example.jpa1.entity.ProductDetailEntity;
import com.example.jpa1.entity.ProductEntity;
import com.example.jpa1.entity.ProviderEntity;
import com.example.jpa1.product.model.ProductRegDto;
import com.example.jpa1.product.model.ProductUpdDto;
import com.example.jpa1.product.model.ProductVo;
import com.example.jpa1.repository.ProductDetailRepository;
import com.example.jpa1.repository.ProductRepository;
import com.example.jpa1.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRep;
    private final ProductDetailRepository productDetailRep;
    private final ProviderRepository providerRep;


    public ProductVo postProduct(ProductRegDto dto) {
        ProviderEntity providerEntity = providerRep.findById(dto.getProviderId()).get();
    /*
        ProviderEntity providerEntity = ProviderEntity.builder()
                .id(dto.getProviderId())
                .build();
*/
        ProductEntity productEntity = ProductEntity.builder()
                .stock(dto.getStock())
                .price(dto.getPrice())
                .name(dto.getName())
                .providerEntity(providerEntity)
                .build();

        productRep.save(productEntity);

        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
                .productEntity(productEntity)
                .description(dto.getDescription())
                .build();

        productDetailRep.save(productDetailEntity);

        log.info("productDetail-number : {} ", productDetailEntity.getNumber());

        return ProductVo.builder()
                .number(productEntity.getNumber())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                //.providerName(productEntity.getProviderEntity().getName())
                .providerName(providerEntity.getName())
                .description(productDetailEntity.getDescription())
                .build();
    }

    public List<ProductVo> getProductAll(String name) {
        //List<ProductEntity> list = productRep.findAll(Sort.by(Sort.Order.desc("number")));
        List<ProductEntity> list = null;
/*
        if(name == null) {
            list = productRep.findAll(Sort.by(Sort.Direction.DESC, "number"));
        } else {
            list = productRep.findAllByName(name, Sort.by(Sort.Direction.DESC, "number"));
        }
*/
        list = productRep.selProductList();
        /*
        List<ProductVo> resultList = new ArrayList();
        for(ProductEntity entity : list) {
            resultList.add(ProductVo.builder()
                    .number(entity.getNumber())
                    .name(entity.getName())
                    .price(entity.getPrice())
                    .stock(entity.getStock())
                    .build());
        }
        return resultList;
         */
        return list.stream().map(entity -> ProductVo.builder()
                                            .number(entity.getNumber())
                                            .name(entity.getName())
                                            .price(entity.getPrice())
                                            .stock(entity.getStock())
                                            .providerName(entity.getProviderEntity().getName())
                                            .description(entity.getProductDetailEntity().getDescription())
                                            .build()
        ).toList();
    }

    public List<ProductVo> getProductAllSearch(String name) {
        List<ProductEntity> list = productRep.findAllByNameContains(name);
        return list.stream().map(entity -> ProductVo.builder()
                .number(entity.getNumber())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build()
        ).toList();
    }

    public ProductVo getProduct(long number) {
        //ProductEntity entity = productRep.getReferenceById(number);
        Optional<ProductEntity> optEntity = productRep.findById(number);
        if(optEntity.isEmpty()) return null;

        ProductEntity entity = optEntity.get();

        return ProductVo.builder()
                .number(entity.getNumber())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }

    public ProductEntity updProduct(ProductUpdDto dto) {
        ProductEntity result = productRep.getReferenceById(dto.getNumber());
        result.setPrice(dto.getPrice());
        result.setName(dto.getName());
        result.setStock(dto.getStock());

        return productRep.save(result);
    }

    public int delProduct(long number) {
        productRep.deleteById(number);
        return 1;
    }
}
