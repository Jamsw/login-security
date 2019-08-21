package com.sparticles.controller;

import com.sparticles.utils.PBKDF2Utils;
import com.sparticles.utils.PasswordUtils;
import com.sparticles.vo.BaseResVO;
import com.sparticles.vo.ResultVOUtils;
import com.sparticles.vo.req.LoginUserVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

/**
 * @Description
 * @Date 2019/8/21 11:07 PM
 * @Auther smart
 **/
@RequestMapping(value = "/")
@RestController
public class RegisterController {

    @PostMapping("/Register")
    public BaseResVO login() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String salt = UUID.randomUUID().toString().replace("-","");
        String password = PasswordUtils.generatePassword();
        String passwordResult = PBKDF2Utils.createPassword(password,salt);
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setMobile("");
        loginUserVO.setPassword(passwordResult);
        return ResultVOUtils.success(loginUserVO);
    }
}
