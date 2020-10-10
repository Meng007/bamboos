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
@TableName(value = "tb_lb")
public class TbLb implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "image")
    private String image;

    @TableField(value = "isDelete")
    @TableLogic
    private Integer isdelete;

    @TableField(value = "state")
    private Integer state;

    @TableField(value = "title")
    private String title;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IMAGE = "image";

    public static final String COL_ISDELETE = "isDelete";

    public static final String COL_STATE = "state";

    public static final String COL_TITLE = "title";
}