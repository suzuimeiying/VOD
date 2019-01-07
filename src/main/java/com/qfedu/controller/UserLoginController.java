package com.qfedu.controller;

import com.qfedu.pojo.UserLogin;
import com.qfedu.service.UserLoginService;
import com.qfedu.util.AES;
import com.qfedu.vo.ResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {
    @Autowired
    UserLoginService userLoginService;

    //添加新的用户----注册
    @RequestMapping("/register.do")
    public ResultVo insert(String name, String password, String email, String tel ){
        ResultVo resultVo = new ResultVo();
        //新的用户
        UserLogin userLogin = new UserLogin();
        userLogin.setName(name);
        userLogin.setPassword(AES.encrypt("8856",password));
        userLogin.setEmail(email);
        userLogin.setTel(tel);
        userLogin.setFlag(1);

        try {
            //添加
            userLoginService.insert(userLogin);
            resultVo.setCode(1);
            resultVo.setMsg("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(0);
            resultVo.setData(e.getMessage());
        }

        return  resultVo;

    }


    //登录验证
    @RequestMapping("/login.do")
    public ResultVo login(String name, String password){

        ResultVo user = userLoginService.login(name,password);
        //完成登录认证
        if(user.getCode()==1) {

            //1、获取主题
            Subject subject= SecurityUtils.getSubject();

            //2、设置登录的Token
            UsernamePasswordToken token=new UsernamePasswordToken(name, password);
            //token.setRememberMe(true);  记住我 将用户名和密码存储到Cookie中

            //3、存储数据到Session
            subject.getSession().setAttribute("user", user.getData());

            //UserLogin user=(UserLogin) SecurityUtils.getSubject().getSession().getAttribute("user");-----获取Token

            //4、登录认证
            subject.login(token);
        }
        return user;
    }

}
