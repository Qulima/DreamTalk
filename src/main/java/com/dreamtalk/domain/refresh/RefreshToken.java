package com.dreamtalk.domain.refresh;

import com.dreamtalk.domain.BaseEntity;
import com.dreamtalk.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "refresh_sessions")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntity {
    @Column(name = "expires_in")
    private Date expiresIn;

    @Column(name = "refresh_token")
    private UUID refreshToken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
