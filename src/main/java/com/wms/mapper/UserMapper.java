package com.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

// 该文件夹用于放置XXXMapper,用于访问数据
// 该文件为示例文件
@Repository // 表示持久层
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 用于注册/修改信息/删除账号时, 查找该账号是否存在
     * @param uaccount
     * @return
     */
    @Select("select * from user_file where u_account = #{uaccount}")
    User searchByUAccount(String uaccount);


    /**
     * 删除人员账号(仅限管理员操作)
     * @param uid
     * @return
     */
    Integer deleteUserInfoByUid(Integer uid);

}
