package com.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

// 该文件夹用于放置实体类
// 该文件为示例文件
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("user_file")
public class User {
    @TableId("u_id")
    private Integer uId;
    private String uAccount;
    private String uPwd;
    private String uName;
    private String sex;
    private Date birthdate;
    private String idNumber;
    private String nationality;
    private String address;
    private String phone;
    private Integer isDelete;

}
