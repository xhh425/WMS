package com.wms.controller;

import com.wms.entity.User;
import com.wms.exception.Result;
import com.wms.exception.ResultUtil;
import com.wms.service.Impl.UserServiceImpl;
import com.wms.service.UserService;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class UserController {
    @Resource
    UserServiceImpl userServiceImpl;

    @Resource
    private UserService userService;

    @RequestMapping("/user/{id}")
    @ResponseBody
    public String queryUserDataById(@PathVariable("id") Integer id) {
        return userService.queryUserDataById(id);
    }

    @GetMapping("/user/login")
    @ResponseBody
    public Result<?> login(@RequestParam String uaccount, @RequestParam String upwd) {
        String msg = userServiceImpl.loginService(uaccount, upwd);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("登录成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/user/register")
    @ResponseBody
    public Result<?> Register(@RequestParam String uaccount, @RequestParam String upwd, @RequestParam String uname, @RequestParam String sex, @RequestParam Date birthdate,
                              @RequestParam String idNumber, @RequestParam String nationality, @RequestParam String address, @RequestParam String phone) {
        User user = new User(null, uaccount, upwd, uname, sex, birthdate, idNumber, nationality, address, phone, 0);
        String msg = userServiceImpl.registerService(user);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("注册成功");
        } else {
            return ResultUtil.error(msg);
        }
    }
}