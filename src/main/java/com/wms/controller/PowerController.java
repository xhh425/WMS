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
public class PowerController {
    @Resource
    PowerServiceImpl powerServiceImpl;
    @Resource
    UserPowerServiceImpl userPowerServiceImpl;

    @GetMapping("/power/addPower")
    @ResponseBody
    public Result<?> addPower(Power power, HttpServletRequest request) {


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 判断权限
//        if ()

            String msg = powerServiceImpl.addPower(power);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("添加权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/power/deletePower")
    @ResponseBody
    public Result<?> deletePower(Power power) {
        System.out.println(power);
        System.out.println(power.getPid());
        System.out.println(power.getPDescribe());
        String msg = powerServiceImpl.deletePower(power);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("删除权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/power/updatePower")
    @ResponseBody
    public Result<?> updatePower(Power power) {
        System.out.println(power);
        System.out.println(power.getPid());
        System.out.println(power.getPDescribe());
        String msg = powerServiceImpl.updatePower(power);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("修改权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/power/findAllPower")
    @ResponseBody
    public Result<?> findAllPower() {
        String msg = powerServiceImpl.findAllPower(null);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("查询权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }
}
