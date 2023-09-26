package com.wms.service;

import com.wms.entity.User;

// 该文件夹用于放置接口
// 该文件为示例文件
public interface UserService {
    /**
     * 登录
     *
     * @param uaccount
     * @param upwd
     * @return
     */
    public String loginService(String uaccount, String upwd);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public String registerService(User user);

    /**
     * 通过uid查找用户
     *
     * @param uid
     * @return
     */
    User queryUserDataById(Integer uid);

    /**
     * 通过uid修改用户基本信息
     *
     * @param uid
     * @return
     */
    String updateUserBaseInfo(Integer uid, User user);

    /**
     * 通过uid修改用户账号密码
     *
     * @param uid
     * @param originUpwd
     * @return
     */
    String updateUpwd(Integer uid, String originUpwd, String updatedUpwd);

    /**
     * 通过uid删除用户信息
     *
     * @param uid
     * @return
     */
    String deleteUserInfo(Integer uid);
}
