package com.wms.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_power")
public class UserPower {
    @TableId(value = "up_id", type = IdType.AUTO)
    private Integer upid;
    @TableField("u_id")
    private Integer uid;
    @TableField("p_id")
    private Integer pid;
}
