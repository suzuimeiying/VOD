package com.qfedu.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class AdminRealm extends AuthorizingRealm{
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //查询当前用户的权限
        //User user = (User)SecurityUtils.getSubject().getSession().getAttribute("admin");
        //设置权限
        //info.addStringPermission(null);

        return null;
    }

    /**
     * 登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        if (upToken.getUsername() != null) {
            return new SimpleAuthenticationInfo(upToken.getUsername(),upToken.getPassword(),getName());
        }
        return null;
    }


}
