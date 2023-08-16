package com.example.jpa1.repository;

import com.example.jpa1.entity.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByName(String name, Sort sort); //쿼리 메서드
    List<ProductEntity> findAllByNameContains(String name);
}
