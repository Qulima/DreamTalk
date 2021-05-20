package com.dreamtalk.domain.user;

import com.dreamtalk.domain.BaseEntity;
import com.dreamtalk.domain.refresh.RefreshToken;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role userRole;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<RefreshToken> refreshTokens;
}
