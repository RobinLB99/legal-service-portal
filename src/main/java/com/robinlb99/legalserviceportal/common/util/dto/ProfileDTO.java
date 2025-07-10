package com.robinlb99.legalserviceportal.common.util.dto;

import com.robinlb99.legalserviceportal.domain.credential.dto.CredentialDTO;
import com.robinlb99.legalserviceportal.domain.user.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private CredentialDTO credentials;
    private UserDTO userData;
}
