package com.wms.service;

import com.wms.entity.UserPower;

public interface UserPowerService {

    String addUserPower(UserPower userPower);

    String deleteUserPower(UserPower userPower);

    String updateUserPower(UserPower userPower);

    String displayUserPower(UserPower userPower);

}
