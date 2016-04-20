package com.meilele.common.shiro.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meilele.common.shiro.dao.UserMapper;
import com.meilele.common.shiro.entity.User;

@SuppressWarnings("unchecked")
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Set<String> findRoles(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        List<Map<String, Object>> obMap = userMapper.selectRolesByUserName(username);
        Set<String> roles = new HashSet<String>();
        for (Map<String, Object> map : obMap) {
            roles.add((String) map.get("roleId"));
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        List<Map<String, Object>> obMap = userMapper.selectPermissionsByUserName(username);
        Set<String> permissions = new HashSet<String>();
        for (Map<String, Object> map : obMap) {
            permissions.add((String) map.get("permission"));
        }
        return permissions;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUserName(username);
    }
}
