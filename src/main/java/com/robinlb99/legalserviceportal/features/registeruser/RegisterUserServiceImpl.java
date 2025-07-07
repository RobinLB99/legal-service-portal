package com.robinlb99.legalserviceportal.features.registeruser;

import org.springframework.stereotype.Service;

import com.robinlb99.legalserviceportal.domain.user.UserEntity;
import com.robinlb99.legalserviceportal.domain.user.UserRepository;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private UserRepository userRepository;

    public RegisterUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean isUserExistsByIdentityNumber(String identityNumber) {
        return userRepository.existsUserByIdentityNumber(identityNumber);
    }

    @Override
    public UserEntity findByIdEntityNumber(String identityNumber) {
        return userRepository.findUserByIdentityNumber(identityNumber).orElse(null);
    }

}
