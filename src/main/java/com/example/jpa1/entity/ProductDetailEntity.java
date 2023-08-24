package com.example.jpa1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDetailEntity {
    @Id
    @Column(name = "product_number")
    private Long number;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_number", updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private ProductEntity productEntity;

    @Column(length = 500)
    private String description;

}
