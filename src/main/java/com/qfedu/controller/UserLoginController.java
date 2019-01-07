package com.qfedu.controller;

import com.qfedu.pojo.UserLogin;
import com.qfedu.service.UserLoginService;
import com.qfedu.vo.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {
    @Autowired
    UserLoginService userLoginService;

    //添加新的用户----注册
    @RequestMapping("/register.do")
    public JsonBean insert(String name,String password,String email,String tel ){
        JsonBean jsonBean = new JsonBean();
        //新的用户
        UserLogin userLogin = new UserLogin();
        userLogin.setName(name);
        userLogin.setPassword(password);
        userLogin.setEmail(email);
        userLogin.setTel(tel);
        userLogin.setFlag(1);

        try {
            //添加
            userLoginService.insert(userLogin);
            jsonBean.setCode(1);
        } catch (Exception e) {
            e.printStackTrace();
            jsonBean.setCode(0);
            jsonBean.setInfo(e.getMessage());
        }

        return  jsonBean;

    }


    //登录验证
    @RequestMapping("/login.do")
    public JsonBean login(String name,String password){
        JsonBean jsonBean = new JsonBean();

        UserLogin user = userLoginService.selectUserLoginByName(name);
        //判断是否存在该用户
        if (user != null) {
            //存在就判断密码正确与否
            if ((user.getPassword()).equals(password)){
                jsonBean.setCode(1);
            }else {
                jsonBean.setCode(0);
                jsonBean.setInfo("账户或密码错误");
            }

        }else {
            jsonBean.setCode(0);
            jsonBean.setInfo("您还没有注册，请先注册");
        }

        return jsonBean;
    }

}
