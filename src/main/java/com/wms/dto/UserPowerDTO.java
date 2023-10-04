package com.wms.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wms.entity.Power;
import com.wms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPowerDTO {
    private Integer uid;
    private String uName;
    private Integer pid;
    private String pDescribe;
}
