package com.sdz.love.bamboos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.sdz.love.bamboos.commons.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_admin")
public class TbAdmin extends BaseDomain {
    /**
     * 管理员主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 管理员账号
     */
    @TableField(value = "username")
    @NotBlank(message = "账号为必填项")
    private String username;

    /**
     * 管理员密码
     */
    @TableField(value = "password")
    @NotBlank(message = "账号为必填项")
    @Length(min = 6,max = 12,message = "密码必须是6-12字符之间")
    private String password;

    /**
     * 管理员类型  1为普通管理员  2为超级管理员
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 管理员头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 管理员电话
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 管理员qq
     */
    @TableField(value = "qq")
    private String qq;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 管理员昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 管理员状态 0正常  1停用
     */
    @TableField(value = "status")
    private String status;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 逻辑删除 0 正常 1删除
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     * 管理员性别 1表示男 2表示女 3表示未知
     */
    @TableField(value = "sex")
    private Integer sex;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_TYPE = "type";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_PHONE_NUMBER = "phone_number";

    public static final String COL_QQ = "qq";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_NICK_NAME = "nick_name";

    public static final String COL_STATUS = "status";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_REMARK = "remark";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_SEX = "sex";
}