package com.wms.controller;

import com.wms.entity.Power;
import com.wms.entity.User;
import com.wms.exception.Result;
import com.wms.exception.ResultUtil;
import com.wms.service.Impl.PowerServiceImpl;
import com.wms.service.Impl.UserServiceImpl;
import com.wms.service.PowerService;
import com.wms.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class PowerController {
    @Resource
    PowerServiceImpl powerServiceImpl;

    @GetMapping("/power/addPower")
    @ResponseBody
    public Result<?> addPower(Power power) {
        String msg = powerServiceImpl.addPower(power);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("添加权限成功");
        } else {
            return ResultUtil.error(msg);
        }
    }
}
