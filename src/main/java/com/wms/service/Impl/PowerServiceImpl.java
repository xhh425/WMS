package com.wms.service.Impl;

import com.wms.entity.Power;
import com.wms.mapper.PowerMapper;
import com.wms.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerServiceImpl implements PowerService {
    /**
     * 操作权限
     * Power
     *
     * @param power
     * @return
     */
    @Autowired
    PowerMapper powerMapper;

    @Override
    public String addPower(Power power) {
        if (powerMapper.insert(power) > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    @Override
    public String deletePower(Power power) {
        if (powerMapper.deleteById(power.getPid()) > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    @Override
    public String updatePower(Power power) {
        if (powerMapper.updateById(power) > 0) {
            return "SUCCESS";
        }
        return "ERROR";

    }

    @Override
    public String findAllPower(List<Power> powerList) {
        powerList = powerMapper.selectList(null);
        if (powerList != null) {
            System.out.println(powerList);
            return "SUCCESS";
        }
        return "ERROR";
    }
}
