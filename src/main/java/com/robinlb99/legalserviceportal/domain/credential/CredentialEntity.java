package com.robinlb99.legalserviceportal.domain.credential;

import java.io.Serializable;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinlb99.legalserviceportal.domain.user.UserEntity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "credentials")
public class CredentialEntity implements Serializable {

    @ToString.Include
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credential")
    private Long id;

    @ToString.Include
    @EqualsAndHashCode.Include
    @Nonnull
    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Nonnull
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Nonnull
    @Column(name = "authorities", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode authorities;

    @ToString.Include
    @Nonnull
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, unique = true)
    private UserEntity user;

}
