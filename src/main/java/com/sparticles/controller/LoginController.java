package com.sparticles.controller;

import com.sparticles.utils.JwtTokenService;
import com.sparticles.utils.PBKDF2Utils;
import com.sparticles.vo.BaseResVO;
import com.sparticles.vo.ResultVOUtils;
import com.sparticles.vo.req.LoginUserVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @Date 2019/8/21 11:08 PM
 * @Auther smart
 **/
@RequestMapping(value = "/abc")
@RestController
public class LoginController {

    @Resource(name = "jwtTokenService")
    private JwtTokenService jwtTokenService;
    @PostMapping("/login")
    public BaseResVO login(@RequestBody LoginUserVO loginUserVO) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String salt = UUID.randomUUID().toString().replace("-","");
        salt="123";
        String passWord = "";
        String passwordResult = PBKDF2Utils.createPassword(loginUserVO.getPassword(),salt);
        String token = jwtTokenService.generateToken(loginUserVO);
        if(passWord.equals(passwordResult)){
            //则登录成功
            //String token = jwtTokenService.generateToken(loginUserVO);
            //给前端token不要过期时间的
        }
        else {
            //则失败
        }
        return ResultVOUtils.success();
    }
}
