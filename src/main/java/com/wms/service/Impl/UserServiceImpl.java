package com.wms.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.Power;
import com.wms.entity.User;
import com.wms.entity.UserPower;
import com.wms.exception.ResultUtil;
import com.wms.mapper.UserMapper;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

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
    public User queryUserDataById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public User queryUserDataByAccount(String Account) {
        User user = userMapper.searchByUAccount(Account);
        return user;
    }


    @Override
    public String updateUserBaseInfo(Integer uid, User user) {
        // 判断账号是否存在
        User selectedUser = userMapper.selectById(uid);
        if (selectedUser == null) {
            return "账号不存在";
        }
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
        if (user == null) {
            return "账号不存在";
        }
        String upwd = user.getUPwd();
//        System.out.println("页面输入框密码: " + originUpwd);
//        System.out.println("原密码: " + upwd);
        // 判断原密码是否正确
        if (!user.getUPwd().equals(DigestUtils.md5DigestAsHex(originUpwd.getBytes()))) {
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
        User user = userMapper.selectById(uid);
        if (user == null) {
            return "账号不存在";
        }
        if (userMapper.deleteUserInfoByUid(uid) > 0) {
            return "SUCCESS";
        }
        return "注销账号失败";
    }

    @Override
    public List<User> findActiveUserInfo() {
        return userMapper.findActiveUserInfo();
    }

    @Override
    public List<User> findInactiveUserInfo() {
        return userMapper.findInactiveUserInfo();
    }

    @Override
    public List<User> findAllUserInfo() {
        return userMapper.findAllUserInfo();
    }
}
