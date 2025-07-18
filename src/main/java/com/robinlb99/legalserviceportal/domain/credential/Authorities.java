package com.robinlb99.legalserviceportal.domain.credential;

import java.util.List;

import com.robinlb99.legalserviceportal.domain.credential.enums.Permission;
import com.robinlb99.legalserviceportal.domain.credential.enums.Role;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Authorities {
    
    @Nonnull
    @Enumerated(EnumType.STRING)
    private Role rol;

    @Enumerated(EnumType.STRING)
    private List<Permission> permissions;

}
