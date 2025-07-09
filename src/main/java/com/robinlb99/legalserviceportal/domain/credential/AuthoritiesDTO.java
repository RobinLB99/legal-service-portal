package com.robinlb99.legalserviceportal.domain.credential;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthoritiesDTO {
    
    @Nonnull
    @Enumerated(EnumType.STRING)
    private Role rol;

    @Enumerated(EnumType.STRING)
    private List<Permission> permissions;

}
