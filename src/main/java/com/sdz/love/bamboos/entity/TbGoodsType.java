package com.sdz.love.bamboos.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_goods_type")
public class TbGoodsType implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "type_name")
    private String typeName;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;

    @TableField(value = "pid")
    private Long pid;


    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TYPE_NAME = "type_name";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_PID = "pid";
}