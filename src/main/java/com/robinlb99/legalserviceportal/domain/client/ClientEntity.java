package com.robinlb99.legalserviceportal.domain.client;

import java.io.Serializable;

import com.robinlb99.legalserviceportal.domain.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class ClientEntity implements Serializable {

    @ToString.Include
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id_client")
    private Long id;

    // @EqualsAndHashCode.Exclude
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id_user")
    private UserEntity user;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "state_or_province", nullable = false, length = 100)
    private String stateOrProvince;

    @ToString.Include
    @Column(name = "country", nullable = false, length = 100)
    private String country;

    public ClientEntity(UserEntity user, String address, String city, String stateOrProvince, String country) {
        this.user = user;
        this.address = address;
        this.city = city;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
    }

}
