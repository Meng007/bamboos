package com.sdz.love.bamboos.commons.config.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sdz.love.bamboos.entity.TbAdmin;
import com.sdz.love.bamboos.entity.TbMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author 13557
 */
@Data
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String USERNAME = "getUsername";
    private static final String PASSWORD = "getPassword";
    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 是否为管理员
     */
    private Boolean isAdmin;

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private TbMember user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        try {
            return (String)user.getClass().getMethod(PASSWORD).invoke(user);
        } catch (Exception e) {
           return "";
        }
    }

    @Override
    public String getUsername() {
        try {
            return (String)user.getClass().getMethod(USERNAME).invoke(user);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public LoginUser(TbMember user, Set<String> permissions){
        this.user=user;
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "token='" + token + '\'' +
                ", loginTime=" + loginTime +
                ", expireTime=" + expireTime +
                ", ipaddr='" + ipaddr + '\'' +
                ", loginLocation='" + loginLocation + '\'' +
                ", browser='" + browser + '\'' +
                ", os='" + os + '\'' +
                ", permissions=" + permissions +
                ", user=" + user +
                '}';
    }
}
