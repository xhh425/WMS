package com.wms.service.Impl;

import com.wms.entity.Power;
import com.wms.mapper.PowerMapper;
import com.wms.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerServiceImpl implements PowerService {
    /**
     * 操作权限
     *
     * @param power
     * @return
     */
    @Autowired
    PowerMapper powerMapper;
    @Override
    public String addPower(Power power) {
        if (false) {
            return "仅限管理员操作";
        }
        powerMapper.insert(power);
        return "ERROR";
    }

    @Override
    public String deletePower(Power power) {
        if (false) {
            return "仅限管理员操作";
        }
        powerMapper.deleteById(power.getPid());
        return "ERROR";
    }

    @Override
    public String updatePower(Power power) {
        if (false) {
            return "仅限管理员操作";
        }
        powerMapper.updateById(power);
        return "ERROR";
    }

    @Override
    public String findAllPower(Power power) {
        if (false) {
            return "仅限管理员操作";
        }
        powerMapper.selectList(null);
        return "ERROR";
    }
}
