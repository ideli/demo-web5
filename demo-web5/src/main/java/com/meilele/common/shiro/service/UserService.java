package com.meilele.common.shiro.service;

import java.util.Set;

import com.meilele.common.shiro.entity.User;

public interface UserService {

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    User findByUsername(String username);

}
