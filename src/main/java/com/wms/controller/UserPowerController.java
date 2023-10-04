package com.wms.controller;

import com.wms.dto.UserPowerDTO;
import com.wms.entity.Power;
import com.wms.entity.User;
import com.wms.entity.UserPower;
import com.wms.exception.Result;
import com.wms.exception.ResultUtil;
import com.wms.service.Impl.PowerServiceImpl;
import com.wms.service.Impl.UserPowerServiceImpl;
import com.wms.service.UserPowerService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserPowerController {
    @Resource
    UserPowerServiceImpl userPowerServiceImpl;


    @GetMapping("/{uid}/power/addUserPower")
    @ResponseBody
    public Result<?> addPower(@PathVariable Integer uid, Integer pid, HttpServletRequest request) {


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResultUtil.error("用户未登录");
        }

        // 判断权限
        if (!userPowerServiceImpl.userHasPowerToManage(userPowerServiceImpl.findUserAllrPower(user.getUId()), "user_power_manage")) {

            return ResultUtil.error("权限不足");
        }

        String msg = userPowerServiceImpl.addUserPower(uid, pid);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("添加用户权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/{uid}/power/deleteUserPower")
    @ResponseBody
    public Result<?> deletePower(@PathVariable Integer uid, Integer pid, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResultUtil.error("用户未登录");
        }

        // 判断权限
        if (!userPowerServiceImpl.userHasPowerToManage(userPowerServiceImpl.findUserAllrPower(user.getUId()), "user_power_manage")) {

            return ResultUtil.error("权限不足");
        }

        String msg = userPowerServiceImpl.deleteUserPower(uid, pid);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("删除用户权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/{uid}/power/updateUserPower")
    @ResponseBody
    public Result<?> updatePower(@PathVariable Integer uid, Integer oldPid, Integer newPid, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResultUtil.error("用户未登录");
        }

        // 判断权限
        if (!userPowerServiceImpl.userHasPowerToManage(userPowerServiceImpl.findUserAllrPower(user.getUId()), "user_power_manage")) {

            return ResultUtil.error("权限不足");
        }

        String msg = userPowerServiceImpl.updateUserPower(uid, oldPid, newPid);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("修改用户权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @GetMapping("/{uid}/power/findUserAllPower")
    @ResponseBody
    public List<UserPowerDTO> findAllPower(@PathVariable Integer uid) {

        return userPowerServiceImpl.findUserAllrPower(uid);
    }
}
