package com.wms.service.Impl;

import com.wms.dto.UserPowerDTO;
import com.wms.entity.UserPower;
import com.wms.mapper.PowerMapper;
import com.wms.mapper.UserPowerMapper;
import com.wms.service.UserPowerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPowerServiceImpl implements UserPowerService {
    @Autowired
    UserPowerMapper userPowerMapper;

    @Override
    public String addUserPower(Integer uid, Integer pid) {
        if (userPowerMapper.addUserPower(uid, pid) > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    @Override
    public String deleteUserPower(Integer uid, Integer pid) {
        if (userPowerMapper.deleteUserPower(uid, pid) > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    @Override
    public String updateUserPower(Integer uid, Integer oldPid, Integer newPid) {
        if (userPowerMapper.updateUserPower(uid, oldPid, newPid) > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    @Override
    public List<UserPowerDTO> findUserAllrPower(Integer uid) {
        return userPowerMapper.findAllUserPower(uid);
    }

    @Override
    public boolean userHasPowerToManage(List<UserPowerDTO> userPowerList, String pDescribe) {
        for (UserPowerDTO userPowerDTO : userPowerList) {
            System.out.println(userPowerDTO.getPDescribe());
            if (userPowerDTO.getPDescribe().equals(pDescribe)) {
                return true;
            }
        }
        return false;
    }


}
