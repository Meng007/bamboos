package com.sdz.love.bamboos.commons.config.service;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 13557
 */
@Data
public class LoginBody {
    @NotBlank(message = "账号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String code;
    private String type;
    private String uuid;
}
