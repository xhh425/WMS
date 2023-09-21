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
    @Select("select * from user_file where u_name = #{uname}")
    User searchByUname(String uname);
}
