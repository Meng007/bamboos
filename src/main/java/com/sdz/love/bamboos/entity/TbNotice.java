package com.sdz.love.bamboos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_notice")
public class TbNotice implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "create_icon")
    private String createIcon;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(value = "state")
    private Integer state;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_CONTENT = "content";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_ICON = "create_icon";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_STATE = "state";
}