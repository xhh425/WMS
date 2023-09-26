package com.wms.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPower {
    @TableId(value = "up_id", type = IdType.AUTO)
    private Integer upid;
    @TableField("u_id")
    private String uid;
    @TableField("p_id")
    private String pid;
}
