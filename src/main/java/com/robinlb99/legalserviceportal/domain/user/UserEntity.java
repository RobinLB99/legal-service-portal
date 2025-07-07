package com.robinlb99.legalserviceportal.domain.user;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @ToString.Include
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @ToString.Include
    @Nonnull
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @ToString.Include
    @Nonnull
    @Column(name = "surnames", nullable = false, length = 50)
    private String surnames;

    @Nonnull
    @Column(name = "id_number", nullable = false, unique = true, length = 20)
    private String idNumber;

    @Nonnull
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @ToString.Include
    @Nonnull
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Nonnull
    @Column(name = "phone_number", nullable = false, length = 25)
    private String phoneNumber;

    public UserEntity(String firstName, String middleName, String surnames, String idNumber, LocalDate birthdate,
            String email, String phoneNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.surnames = surnames;
        this.idNumber = idNumber;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserEntity(String firstName, String surnames, String idNumber, LocalDate birthdate, String email,
            String phoneNumber) {
        this.firstName = firstName;
        this.surnames = surnames;
        this.idNumber = idNumber;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
