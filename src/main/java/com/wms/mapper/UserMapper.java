package com.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

// 该文件夹用于放置XXXMapper,用于访问数据
// 该文件为示例文件
@Repository // 表示持久层
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 用于注册/修改信息/删除账号时, 查找该账号是否存在
     *
     * @param uaccount
     * @return
     */
    @Select("select * from user_file where u_account = #{uaccount}")
    User searchByUAccount(@Param("uaccount") String uaccount);


    /**
     * 删除人员账号(仅限管理员操作)
     *
     * @param uid
     * @return
     */
    @Update("update user_file set is_delete = 1 where u_id = #{uid}")
    Integer deleteUserInfoByUid(@Param("uid") Integer uid);


    @Select("select * from user_file where is_delete = 0")
    List<User> findActiveUserInfo();

    @Select("select * from user_file where is_delete = 1")
    List<User> findInactiveUserInfo();

    @Select("select * from user_file")
    List<User> findAllUserInfo();

}
