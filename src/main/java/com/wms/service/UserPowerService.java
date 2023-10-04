package com.wms.service;

import com.wms.dto.UserPowerDTO;
import com.wms.entity.UserPower;

import java.util.List;

public interface UserPowerService {

    String addUserPower(Integer uid, Integer pid);

    String deleteUserPower(Integer uid, Integer pid);

    String updateUserPower(Integer uid, Integer oldPid, Integer newPid);

    List<UserPowerDTO> findUserAllrPower(Integer uid);

    boolean userHasPowerToManage(List<UserPowerDTO> userPowerList, String pDescribe);

}
