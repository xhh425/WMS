package com.wms.controller;

import com.wms.entity.Power;
import com.wms.entity.User;
import com.wms.exception.Result;
import com.wms.exception.ResultUtil;
import com.wms.service.Impl.PowerServiceImpl;
import com.wms.service.Impl.UserPowerServiceImpl;
import com.wms.service.Impl.UserServiceImpl;
import com.wms.service.PowerService;
import com.wms.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/power")
public class PowerController {
    @Resource
    PowerServiceImpl powerServiceImpl;
    @Resource
    UserPowerServiceImpl userPowerServiceImpl;

    @GetMapping("/addPower")
    @ResponseBody
    public Result<?> addPower(Power power, HttpServletRequest request) {


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultUtil.error("用户未登录");
        }
        Integer uid = user.getUId();

        // 判断权限
        if (!userPowerServiceImpl.userHasPowerToManage( userPowerServiceImpl.findUserAllrPower(uid), "power_manage")) {

            return ResultUtil.error("权限不足");
        }

        String msg = powerServiceImpl.addPower(power);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("添加权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/deletePower")
    @ResponseBody
    public Result<?> deletePower(Power power, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultUtil.error("用户未登录");
        }
        Integer uid = user.getUId();

        // 判断权限
        if (!userPowerServiceImpl.userHasPowerToManage( userPowerServiceImpl.findUserAllrPower(uid), "power_manage")) {

            return ResultUtil.error("权限不足");
        }

//        System.out.println(power);
//        System.out.println(power.getPid());
//        System.out.println(power.getPDescribe());
        String msg = powerServiceImpl.deletePower(power);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("删除权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/updatePower")
    @ResponseBody
    public Result<?> updatePower(Power power, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultUtil.error("用户未登录");
        }
        Integer uid = user.getUId();

        // 判断权限
        if (!userPowerServiceImpl.userHasPowerToManage( userPowerServiceImpl.findUserAllrPower(uid), "power_manage")) {

            return ResultUtil.error("权限不足");
        }

//        System.out.println(power);
//        System.out.println(power.getPid());
//        System.out.println(power.getPDescribe());
        String msg = powerServiceImpl.updatePower(power);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("修改权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/findAllPower")
    @ResponseBody
    public List<Power> findAllPower() {
        return powerServiceImpl.findAllPower();
//        if (("SUCCESS").equals(msg)) {
//            return ResultUtil.success("查询权限成功");
//        } else {
//            return ResultUtil.error(msg);
//        }
    }
}
