package com.xz.video.common.Realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.common.utils.UserUtils;
import com.xz.video.entity.UserEntity;
import com.xz.video.service.RoleService;
import com.xz.video.service.UserService;
import com.xz.video.common.utils.JWTToken;
import com.xz.video.common.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    UserUtils userUtils;

    /*
     必须要加，不然程序会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
    /*
    这里的PrincipalCollection对应SimpleAuthenticationInfo的第一个参数
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        User user=(User)principal.getPrimaryPrincipal();
//        System.out.println(user);

        String username = JwtUtils.getUsername(principal.toString());
        UserEntity user = userService.getOne(new QueryWrapper<UserEntity>().eq("username", username));

        //添加角色和权限
        List<String> roles = userUtils.getRole(user);
        Set<String> permissions = userUtils.getPermission(user);
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtils.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }
        UserEntity user = userService.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}