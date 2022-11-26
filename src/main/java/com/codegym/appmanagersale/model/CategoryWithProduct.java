package com.codegym.appmanagersale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWithProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    @ManyToOne()
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;
}
