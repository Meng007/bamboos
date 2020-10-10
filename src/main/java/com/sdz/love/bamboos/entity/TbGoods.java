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
@TableName(value = "tb_goods")
public class TbGoods implements Serializable {
    /**
     * 物品主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 物品类型
     */
    @TableField(value = "goods_type")
    private String goodsType;

    /**
     * 发布人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 发布时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 头像
     */
    @TableField(value = "create_icon")
    private String createIcon;

    /**
     * 状态 1 丢失 2 捡东西 3 找到失主
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 逻辑删除 0 ，1 删除
     */
    @TableField(value = "delete_flag")
    private Integer deleteFlag;

    /**
     * 物品图片
     */
    @TableField(value = "goods_icon")
    private String goodsIcon;

    /**
     * 发布人id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 启示类型，l 丢失 t 捡的
     */
    @TableField(value = "type")
    private String type;

    @TableField(value = "time")
    private Date time;

    @TableField(value = "phone")
    private String phone;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_GOODS_NAME = "goods_name";

    public static final String COL_GOODS_TYPE = "goods_type";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_ICON = "create_icon";

    public static final String COL_STATUS = "status";

    public static final String COL_DELETE_FLAG = "delete_flag";

    public static final String COL_GOODS_ICON = "goods_icon";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ADDRESS = "address";

    public static final String COL_REMARKS = "remarks";

    public static final String COL_TYPE = "type";

    public static final String COL_TIME = "time";

    public static final String COL_PHONE = "phone";
}