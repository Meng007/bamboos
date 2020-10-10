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
@TableName(value = "tb_love")
public class TbLove implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "love_title")
    private String loveTitle;

    @TableField(value = "content")
    private String content;

    @TableField(value = "icons")
    private String icons;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;

    @TableField(value = "state")
    private Integer state;

    @TableField(value = "create_id")
    private Long createId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_LOVE_TITLE = "love_title";

    public static final String COL_CONTENT = "content";

    public static final String COL_ICONS = "icons";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_ICON = "icon";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_STATE = "state";

    public static final String COL_CREATE_ID = "create_id";
}