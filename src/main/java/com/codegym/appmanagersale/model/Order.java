package com.codegym.appmanagersale.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    public static final String CONFIRMED = "CONFIRMED";
    public static final String DELIVERED = "DELIVERED";
    public static final String CANCELLED = "CANCELLED";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_At")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(columnDefinition = "nvarchar(200) not null")
    private String address;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;

    @Transient
    private long total;

    private String status;

    @PrePersist
    public void preCreate() {
        createdAt = new Date();
        status = CONFIRMED;
    }
}
