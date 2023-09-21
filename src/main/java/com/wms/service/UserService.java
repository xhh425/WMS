package com.wms.service;

import com.wms.entity.User;

// 该文件夹用于放置接口
// 该文件为示例文件
public interface UserService {
    public String loginService(String uname, String upwd);
    public String registerService(User user);
    String queryUserDataById(Integer id);

}
