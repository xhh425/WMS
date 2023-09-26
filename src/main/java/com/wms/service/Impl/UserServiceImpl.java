package com.wms.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.User;
import com.wms.exception.ResultUtil;
import com.wms.mapper.UserMapper;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

// 该文件夹用于放置实现类
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
            if (user.getUPwd().equals(DigestUtils.md5DigestAsHex(upwd.getBytes()))) {
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
//        System.out.println(user);
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
                System.out.println(e);
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

    @Override
    public String updateUserBaseInfo(Integer uid, User user) {
        User selectedUser = userMapper.selectById(uid);
        System.out.println("页面输入框数据: " + user);
        if (selectedUser != null) {
            userMapper.updateById(user);
            System.out.println("原信息:" + selectedUser + "\n修改后信息:" + userMapper.selectById(uid));
            return "SUCCESS";
        }
        return "更新用户信息失败";
    }

    @Override
    public String updateUpwd(Integer uid, String originUpwd, String updatedUpwd) {
        User user = userMapper.selectById(uid);
        String upwd = user.getUPwd();
//        System.out.println("页面输入框密码: " + originUpwd);
//        System.out.println("原密码: " + upwd);
        // 判断原密码是否正确
        if (!originUpwd.equals(user.getUPwd())) {
            return "原密码错误, 请重新输入";
        }
        if (user != null) {

            // 修改操作
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("u_id", uid).set("u_pwd", updatedUpwd);
            Integer row = userMapper.update(user, updateWrapper);
//            System.out.println("!!!!" + row);

            System.out.println("原密码:" + upwd + "\n修改后密码:" + userMapper.selectById(uid).getUPwd());
            return "SUCCESS";
        }
        return "修改用户账号密码失败";
    }

    @Override
    public String deleteUserInfo(Integer uid) {
        return "删除用户信息失败";
    }
}
