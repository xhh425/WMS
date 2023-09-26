package com.wms.controller;

import com.wms.entity.User;
import com.wms.exception.Result;
import com.wms.exception.ResultUtil;
import com.wms.service.Impl.UserServiceImpl;
import com.wms.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class UserController {
    @Resource
    UserServiceImpl userServiceImpl;


    @GetMapping("/user/{id}")
    @ResponseBody
    public User Index(@PathVariable("id") Integer id, HttpServletRequest request) {
        System.out.println("进入主页");
        HttpSession session = request.getSession();
        User user = userServiceImpl.queryUserDataById(id);
        // 通过Session判断是否为登录用户进入
        if (session.getAttribute("username") == null) {
            System.out.println("请登录");
            return null;
        } else if (user.getUPwd().equals(session.getAttribute("userpassword"))) {
            System.out.println("session: " + session.getAttribute("username"));
            System.out.println("当前访问账号: " + user.getUAccount());
            return userServiceImpl.queryUserDataById(id);
        }
        return null;
    }

    @GetMapping("/user/login")
    @ResponseBody
    public Result<?> login(HttpServletRequest request, @RequestParam String uaccount, @RequestParam String upwd) {
        String msg = userServiceImpl.loginService(uaccount, upwd);
        if (("SUCCESS").equals(msg)) {
            // 用session记录用户登陆状态
            HttpSession session = request.getSession();
            session.setAttribute("username", uaccount);
            session.setAttribute("userpassword", DigestUtils.md5DigestAsHex(upwd.getBytes()));
            System.out.println(session.getAttribute("username").toString() + session.getAttribute("userpassword").toString());
            return ResultUtil.success("登录成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/user/register")
    @ResponseBody
    public Result<?> Register(@RequestParam String uaccount, @RequestParam String upwd, @RequestParam String uname, @RequestParam String sex, @RequestParam Date birthdate,
                              @RequestParam String idNumber, @RequestParam String nationality, @RequestParam String address, @RequestParam String phone) {
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

    @GetMapping("/user/updateUserBaseInfo")
    @ResponseBody
    public Result<?> updateUserBaseInfo(Integer uid, String upwd, String uname, String sex, Date birthdate,
                                        String idNumber, String nationality, String address, String phone) {
        // 1.判断权限
        if (false) {
            return ResultUtil.error("无权限, 仅限本人账号及管理员操作");
        }
        // 2.进行修改操作
        User user = new User(uid, null, upwd, uname, sex, birthdate, idNumber, nationality, address, phone, null);
        System.out.println("controller: " + user);
        String msg = userServiceImpl.updateUserBaseInfo(uid, user);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("修改用户信息成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/user/updateUpwd")
    @ResponseBody
    public Result<?> updateUpwd(Integer uid, String originUpwd, String updatedUpwd) {
        // 1.判断权限
        if (false) {
            return ResultUtil.error("无权限, 仅限本人账号及管理员操作");
        }
        // 2.进行修改操作
        String msg = userServiceImpl.updateUpwd(uid, originUpwd, updatedUpwd);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("修改用户密码成功");
        } else {
            return ResultUtil.error(msg);
        }
    }
}