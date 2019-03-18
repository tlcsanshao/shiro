package com.sanshao.shiro.shirodemo;

import com.sanshao.shiro.bean.auth.Permission;
import com.sanshao.shiro.bean.auth.Role;
import com.sanshao.shiro.bean.auth.User;
import com.sanshao.shiro.repository.auth.UserRepository;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class SimpleShiroRealm extends AuthorizingRealm {
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
        System.out.println("认证模块被调用");
        String username = (String) token.getPrincipal();
        char tokenPassword[] = ((UsernamePasswordToken) token).getPassword();
        System.out.println("username=" + username + ",password=" + String.valueOf(tokenPassword));

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
        System.out.println("授权模块被调用");
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        Set<Role> roles = user.getRoles();
        Set<String> roleNames = new HashSet<String>();
        for (Role role : roles) {
            roleNames.add(role.getRolename());
        }
        simpleAuthorizationInfo.setRoles(roleNames);


        Set<Permission> permissionSet = new HashSet<Permission>();
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                if (!permissionSet.contains(permission)) {
                    permissionSet.add(permission);
                }
            }
        }
        Set<String> permissionNames = new HashSet<String>();
        for (Permission permission : permissionSet) {
            permissionNames.add(permission.getPermissionname());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionNames);

        return simpleAuthorizationInfo;
    }


}
