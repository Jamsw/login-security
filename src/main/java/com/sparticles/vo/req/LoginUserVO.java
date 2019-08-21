package com.sparticles.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Date 2019/8/21 11:14 PM
 * @Auther smart
 **/
@Data
public class LoginUserVO {

    @NotNull(message = "手机号不能为空")
    private String mobile;

    @NotNull(message = "密码不能为空")
    private String password;
}
