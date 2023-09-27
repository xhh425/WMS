package com.wms.service;

import com.wms.entity.Power;

import java.util.List;

public interface PowerService {

    String addPower(Power power);

    String deletePower(Power power);

    String updatePower(Power power);

    String findAllPower(List<Power> powerList);
}
