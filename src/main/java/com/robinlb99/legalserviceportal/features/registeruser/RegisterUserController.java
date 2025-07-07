package com.robinlb99.legalserviceportal.features.registeruser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.robinlb99.legalserviceportal.domain.user.UserEntity;

@Controller
public class RegisterUserController {

    private RegisterUserServiceImpl registerUserService;

    public RegisterUserController(RegisterUserServiceImpl registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping("/registar_usuario")
    public String registarUsuario(UserEntity usuario) {
        boolean existsUser = registerUserService
                .isUserExistsByIdentityNumber(usuario.getIdNumber());

        if (!existsUser) {
            registerUserService.createUser(usuario);
            return "";
        }

        return "";
    }

}
