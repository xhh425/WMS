package com.wms.service.Impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.User;
import com.wms.mapper.UserMapper;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 该文件夹用于放置实现类
// 该文件为示例文件
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String loginService(String uname, String upwd) {
        if ("".equals(uname)) {
            return "请输入用户名";
        } else if ("".equals(upwd)) {
            return "请输入密码";
        }
        User user = userMapper.searchByUname(uname);
        System.out.println(user);
        if (user != null) {
            if (upwd.equals(user.getUPwd())) {
                return "SUCCESS";
            } else {
                System.out.println(user.getUPwd());
                return "密码错误";
            }
        }
        return "此用户不存在";
    }

    @Override
    public String registerService(User user) {
        if ("".equals(user.getUName())) {
            return "请输入用户名";
        } else if ("".equals(user.getUName())) {
            return "请输入密码";
        }
        User userE = userMapper.searchByUname(user.getUName());
        if (userE == null) {
            userMapper.insert(user);
            return "SUCCESS";
        }
        return "用户已存在";
    }

    @Override
    public String queryUserDataById(Integer id) {
        return "已找到id=" + id.toString() + "的用户";
    }
}
