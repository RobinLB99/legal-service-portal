package com.robinlb99.legalserviceportal.domain.credential;

import java.util.List;

import com.robinlb99.legalserviceportal.domain.credential.enums.Permission;
import com.robinlb99.legalserviceportal.domain.credential.enums.Role;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authorities {
    
    @Nonnull
    @Enumerated(EnumType.STRING)
    private Role rol;

    private List<Permission> permissions;

}
