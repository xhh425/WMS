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
    public String loginService(String uaccount, String upwd) {
        if ("".equals(uaccount)) {
            return "请输入账号";
        } else if ("".equals(upwd)) {
            return "请输入密码";
        }
        User user = userMapper.searchByUAccount(uaccount);
        System.out.println(user);
        if (user != null) {
            if (upwd.equals(user.getUPwd())) {
                return "SUCCESS";
            } else {
                System.out.println(user.getUPwd());
                return "密码错误";
            }
        }
        return "该账号不存在";
    }

    @Override
    public String registerService(User user) {
        System.out.println(user);
        if ("".equals(user.getUAccount())) {
            return "请输入账号";
        } else if ("".equals(user.getUAccount())) {
            return "请输入密码";
        }
        User userE = userMapper.searchByUAccount(user.getUAccount());
        if (userE == null) {
            try {
                userMapper.insert(user);
            } catch (Exception e) {
                return "请检查输入格式";
            }
            return "SUCCESS";
        }
        return "用户已存在";
    }

    @Override
    public String queryUserDataById(Integer id) {
        return "已找到id=" + id.toString() + "的用户";
    }
}
