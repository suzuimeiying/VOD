package com.qfedu.service;

import com.qfedu.pojo.UserLogin;
import com.qfedu.vo.ResultVo;

public interface UserLoginService {

    //添加新的用户
    int insert(UserLogin record);

    //登录验证---查找用户名对比用户是否存在,密码是否正确
    ResultVo login(String userName, String password);

}
