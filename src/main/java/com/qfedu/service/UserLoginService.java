package com.qfedu.service;

import com.qfedu.pojo.UserLogin;

public interface UserLoginService {

    //添加新的用户
    int insert(UserLogin record);

    //登录验证---查找用户名对比用户是否存在
    UserLogin selectUserLoginByName(String userName);

}
