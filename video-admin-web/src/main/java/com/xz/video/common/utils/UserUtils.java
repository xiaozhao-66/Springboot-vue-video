package com.xz.video.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.entity.*;
import com.xz.video.service.FunctionService;
import com.xz.video.service.RoleFunctionRelationService;
import com.xz.video.service.RoleService;
import com.xz.video.service.UserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.Action;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserUtils {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleRelationService userRoleRelationService;

    @Autowired
    RoleFunctionRelationService roleFunctionRelationService;

    @Autowired
    FunctionService functionService;

    /**
     * 得到用户所属角色
     */
    public List<String> getRole(UserEntity userEntity) {
        List<UserRoleRelationEntity> list = userRoleRelationService.list(new QueryWrapper<UserRoleRelationEntity>().eq("user_id", userEntity.getId()));
        return list.stream().map(UserRoleRelationEntity::getRolename).collect(Collectors.toList());
    }

    /**
     * 得到用户所拥有的权限
     */
    public Set<String> getPermission(UserEntity userEntity) {
        Set<String> set = new HashSet<>();
        List<UserRoleRelationEntity> list = userRoleRelationService.list(new QueryWrapper<UserRoleRelationEntity>().eq("user_id", userEntity.getId()));
        List<String> roleIds = list.stream().map(UserRoleRelationEntity::getRoleId).collect(Collectors.toList());

        for (String role : roleIds) {
            List<RoleFunctionRelationEntity> roleFunctionRelations = roleFunctionRelationService.list(new QueryWrapper<RoleFunctionRelationEntity>().eq("role_id", role));
            List<String> functionIds = roleFunctionRelations.stream().map(RoleFunctionRelationEntity::getFunctionId).collect(Collectors.toList());
            List<FunctionEntity> functions = functionService.listByIds(functionIds);
            List<String> permissions = functions.stream().map(FunctionEntity::getPermission).collect(Collectors.toList());
            for (String e : permissions) {
                set.addAll(split(e));
            }
        }
        return set;
    }

    private List<String> split(String s) {
        String[] split = s.split(";");
        return Arrays.asList(split);
    }

}
