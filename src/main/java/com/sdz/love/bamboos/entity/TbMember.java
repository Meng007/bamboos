package com.sdz.love.bamboos.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_member")
public class TbMember implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "nick_name")
    private String nickName;

    @TableField(value = "sex")
    private String sex;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "qq")
    private String qq;

    @TableField(value = "email")
    private String email;

    @TableField(value = "address")
    private String address;
    /**
     *  用户状态 a 正常， b禁用
     */
    @TableField(value = "status")
    private String status;

    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "is_admin")
    private Integer isAdmin;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_NICK_NAME = "nick_name";

    public static final String COL_SEX = "sex";

    public static final String COL_PHONE = "phone";

    public static final String COL_QQ = "qq";

    public static final String COL_EMAIL = "email";

    public static final String COL_ADDRESS = "address";

    public static final String COL_STATUS = "status";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_ICON = "icon";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_ADMIN = "is_admin";
}