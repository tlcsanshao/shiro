package com.mars.ums.gateway.shiro;

import com.mars.ums.jpa.bean.auth.User;
import com.mars.ums.jpa.repository.auth.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserRepository userRepository;

    //支持UsernamePasswordToken
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) {
        //从token中 获取用户身份信息.密碼可以不需要。拿到用戶名從數據庫中取出密碼。
        String username = (String) token.getPrincipal();
        char tokenPassword[] = ((UsernamePasswordToken) token).getPassword();
        System.out.println("username="+username+",password="+String.valueOf(tokenPassword));

        if (username == null) {
            return null;
        }
        //拿username从数据库中查询
        User dbuser = userRepository.findByUsername(username);
        //如果查询不到则返回null
        if (dbuser == null) {
            return null;
        }
        //获取从数据库查询出来的用户密码
        String password = dbuser.getPassword();
        String salt = username + "mars"; //salt生成策略
        //返回认证信息由父类AuthenticatingRealm进行认证
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                username, password, ByteSource.Util.bytes(salt), getName());

        return simpleAuthenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }
}
