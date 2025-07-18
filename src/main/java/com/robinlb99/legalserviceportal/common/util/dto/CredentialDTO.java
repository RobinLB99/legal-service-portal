package com.robinlb99.legalserviceportal.common.util.dto;

import com.robinlb99.legalserviceportal.domain.credential.Authorities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialDTO {
    private String username;
    private Authorities authorities;
}
