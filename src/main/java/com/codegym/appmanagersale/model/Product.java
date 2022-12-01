package com.codegym.appmanagersale.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    public static final String AVAILABLE = "AVAILABLE";
    public static final String UNAVAILABLE = "UNAVAILABLE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;

    @Column(name = "price_in", nullable = false)
    private Long priceIn;

    @Column(name = "price_out", nullable = false)
    private Long priceOut;

    @Column(nullable = false)
    private Integer quantity;

    @Column(columnDefinition = "longtext")
    private String image;

    @Column(columnDefinition = "longtext")
    private String description;
    private String status;

    @OneToMany(mappedBy = "product")
    List<CategoryWithProduct> categoryWithProducts;

    public List<CategoryWithProduct> getCategoryWithProducts() {
        return categoryWithProducts;
    }

    @OneToMany(mappedBy = "product")
    Set<OrderDetail> orderDetails;

    @PrePersist
    public void preCreate() {
        status = AVAILABLE;
    }
}
