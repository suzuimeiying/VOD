package com.qfedu.service.impl;

import com.qfedu.dao.UserLoginMapper;
import com.qfedu.pojo.UserLogin;
import com.qfedu.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginMapper userLoginDao;


    @Override
    public int insert(UserLogin record) {
        userLoginDao.insert(record);
        return 0;
    }

    @Override
    public UserLogin selectUserLoginByName(String userName) {
        return null;
    }
}
