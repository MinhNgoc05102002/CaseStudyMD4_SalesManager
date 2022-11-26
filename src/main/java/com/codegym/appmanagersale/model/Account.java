package com.codegym.appmanagersale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, columnDefinition = "nvarchar(50)")
    private String fullName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(columnDefinition = "nvarchar(200)")
    private String address;
    private Integer active;
    @Column(name = "user_role", length = 20)
    private String userRole;

    @Transient
    private String confirmPassword;

    @PrePersist
    public void preCreate() {
        active = ACTIVE;
        userRole = USER;
    }
}
