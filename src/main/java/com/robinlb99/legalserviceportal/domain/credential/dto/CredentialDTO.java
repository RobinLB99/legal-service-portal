package com.robinlb99.legalserviceportal.domain.credential.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialDTO {
    private String username;
    private AuthoritiesDTO authorities;
}
