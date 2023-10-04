package com.wms.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.User;
import com.wms.exception.Result;
import com.wms.exception.ResultUtil;
import com.wms.service.Impl.UserPowerServiceImpl;
import com.wms.service.Impl.UserServiceImpl;
import com.wms.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserServiceImpl userServiceImpl;
    @Resource
    UserPowerServiceImpl userPowerServiceImpl;


    @GetMapping("/{uid}")
    @ResponseBody
    public User Index(@PathVariable("id") Integer uid, HttpServletRequest request) {
        System.out.println("进入用户id为:" + uid + "的主页");
        HttpSession session = request.getSession();
        User user = userServiceImpl.queryUserDataById(uid);
        // 通过Session判断是否为登录用户进入
        if (session.getAttribute("uaccount") == null) {
            System.out.println("请登录");
            return null;
        } else if (user.getUPwd().equals(session.getAttribute("upwd"))) {
            System.out.println("session: " + session.getAttribute("uaccount"));
            System.out.println("当前访问账号: " + user.getUAccount());
            return userServiceImpl.queryUserDataById(uid);
        }
        return null;
    }

    @GetMapping("/login")
    @ResponseBody
    public Result<?> login(HttpServletRequest request, String uaccount, String upwd) {
        String msg = userServiceImpl.loginService(uaccount, upwd);
        if (("SUCCESS").equals(msg)) {

            User user = userServiceImpl.queryUserDataByAccount(uaccount);

            // 用session记录用户登陆状态
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            return ResultUtil.success("登录成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/register")
    @ResponseBody
    public Result<?> Register(String uaccount, String upwd, String uname, String sex, Date birthdate,
                              String idNumber, String nationality, String address, String phone) {
        //使用spring提供的DigestUtils工具类生成32位MD5字符串
        String md5upwd = DigestUtils.md5DigestAsHex(upwd.getBytes());
        User user = new User(null, uaccount, md5upwd, uname, sex, birthdate, idNumber, nationality, address, phone, 0);
        String msg = userServiceImpl.registerService(user);
        if (("SUCCESS").equals(msg)) {
            System.out.println(user.getUPwd());
            return ResultUtil.success("注册成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/{uid}/updateUserBaseInfo")
    @ResponseBody
    public Result<?> updateUserBaseInfo(@PathVariable Integer uid,String uAccount, String uname, String sex, Date birthdate,
                                        String idNumber, String nationality, String address, String phone, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultUtil.error("用户未登录");
        }

        // 1.判断权限
        if (!userPowerServiceImpl.userHasPowerToManage(userPowerServiceImpl.findUserAllrPower(uid), "user_manage") || user.getUId() == uid) {
            return ResultUtil.error("无权限, 仅限本人账号及管理员操作");
        }
        // 2.进行修改操作
        User newUser = new User(uid, uAccount, null, uname, sex, birthdate, idNumber, nationality, address, phone, null);
        System.out.println("controller: " + newUser);
        String msg = userServiceImpl.updateUserBaseInfo(uid, newUser);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("修改用户信息成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/{uid}/updateUpwd")
    @ResponseBody
    public Result<?> updateUpwd(@PathVariable Integer uid, String originUpwd, String updatedUpwd, String checkUpwd, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultUtil.error("用户未登录");
        }

        // 1.判断是否为本人操作
        if (user.getUId() != uid) {
            return ResultUtil.error("无权限, 仅限本人账号及管理员操作");
        }
        // 2.确认两次密码输入一致
        if (!updatedUpwd.equals(checkUpwd)) {
            return ResultUtil.error("两次密码输入不一致");
        }

        // 3.进行修改操作
        String msg = userServiceImpl.updateUpwd(uid, originUpwd, updatedUpwd);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("修改用户密码成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/{uid}/deleteUserInfo")
    @ResponseBody
    public Result<?> deleteUserInfo(@PathVariable Integer uid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultUtil.error("用户未登录");
        }

        // 1.判断是否为本人操作
        if (!userPowerServiceImpl.userHasPowerToManage(userPowerServiceImpl.findUserAllrPower(uid), "user_manage") || user.getUId() == uid) {
            return ResultUtil.error("无权限, 仅限本人账号及管理员操作");
        }

        // 2.进行删除操作
        String msg = userServiceImpl.deleteUserInfo(uid);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("注销账号成功");
        } else {
            return ResultUtil.error("注销账号失败");
        }
    }
    @GetMapping("/findActiveUserInfo")
    @ResponseBody
    public List<User> findActiveUserInfo() {
        return userServiceImpl.findActiveUserInfo();
    }
    @GetMapping("/findInactiveUserInfo")
    @ResponseBody
    public List<User> findInactiveUserInfo() {
        return userServiceImpl.findInactiveUserInfo();
    }
    @GetMapping("/findAllUserInfo")
    @ResponseBody
    public List<User> findAllUserInfo() {
        return userServiceImpl.findAllUserInfo();
    }
}