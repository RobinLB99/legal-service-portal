package com.robinlb99.legalserviceportal.domain.lawyer;

import java.io.Serializable;

import com.robinlb99.legalserviceportal.domain.user.UserEntity;

import jakarta.persistence.CascadeType;
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

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "lawyers")
public class LawyerEntity implements Serializable {

    @ToString.Include
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id_lawyer")
    private Long id;

    // @ToString.Exclude
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lawyer", referencedColumnName = "id_user")
    private UserEntity user;

    @Column(name = "licence", nullable = false, unique = true, length = 50)
    private String licence;

    @ToString.Include
    @Column(name = "specialization", nullable = false, length = 255)
    private String specialization;

}
