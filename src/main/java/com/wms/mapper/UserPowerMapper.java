package com.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.dto.UserPowerDTO;
import com.wms.entity.User;
import com.wms.entity.UserPower;
import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 表示持久层
@Mapper
public interface UserPowerMapper extends BaseMapper {
    // 根据用户id操作
    // 增加用户权限
    @Insert("INSERT INTO user_power (u_id, p_id) VALUES (#{uid}, #{pid})")
    Integer addUserPower(@Param("uid") Integer uid, @Param("pid") Integer pid);

    // 删除用户权限
    @Delete("DELETE FROM user_power WHERE u_id = #{uid} AND p_id = #{pid}")
    Integer deleteUserPower(@Param("uid") Integer uid, @Param("pid") Integer pid);

    // 修改用户权限
    @Update("UPDATE user_power\n" +
            "SET p_id = #{newPid}\n" +
            "WHERE u_id = #{uid} AND p_id = #{oldPid};")
    Integer updateUserPower(@Param("uid") Integer uid, @Param("oldPid") Integer oldPid, @Param("newPid") Integer newPid);

    // 查找用户权限
    @Select("SELECT u.u_id,u.u_name,p.p_id,p.p_describe\n" +
            "FROM user_file u\n" +
            "         LEFT JOIN user_power up ON u.u_id = up.u_id\n" +
            "         LEFT JOIN power p ON up.p_id = p.p_id\n" +
            "WHERE u.u_id = #{uid}")
    List<UserPowerDTO> findAllUserPower(@Param("uid") Integer uid);

}
