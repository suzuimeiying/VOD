package com.qfedu.service.impl;

import com.qfedu.dao.UserLoginMapper;
import com.qfedu.pojo.UserLogin;
import com.qfedu.service.UserLoginService;
import com.qfedu.util.AES;
import com.qfedu.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginMapper userLoginDao;

    //添加新的用户---注册
    @Override
    public int insert(UserLogin record) {
        userLoginDao.insert(record);
        return 0;
    }


    //登录验证---查找用户名对比用户是否存在,密码是否正确
    @Override
    public ResultVo login(String userName, String password) {

        UserLogin user = userLoginDao.selectUserLoginByName(userName);
        //判断是否存在该用户
        if (user != null) {
            //存在就判断密码正确与否
            if ((user.getPassword()).equals(AES.decrypt("8856", password))) {
                //登录成功
                return ResultVo.setOK(user);
            }
        }

        return ResultVo.setERROR();
    }
}
