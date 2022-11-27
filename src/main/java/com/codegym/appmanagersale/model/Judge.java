package com.codegym.appmanagersale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "judges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Judge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rate;

    @Column(columnDefinition = "nvarchar(200)")
    private String comment;

    @Temporal(TemporalType.DATE)
    @Column(name = "judge_At")
    private Date judgeAt;

    @OneToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @PrePersist
    public void preCreate() {
        judgeAt = new Date();
    }
}
