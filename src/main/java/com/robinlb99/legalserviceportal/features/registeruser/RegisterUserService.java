package com.robinlb99.legalserviceportal.features.registeruser;

import com.robinlb99.legalserviceportal.domain.user.UserEntity;

public interface RegisterUserService {

    UserEntity createUser(UserEntity user);

    Boolean isUserExistsByIdentityNumber(String identityNumber);

    UserEntity findByIdEntityNumber(String identityNumber);

}
