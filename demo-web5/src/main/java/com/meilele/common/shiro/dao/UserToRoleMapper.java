package com.meilele.common.shiro.dao;

import com.meilele.common.shiro.entity.UserToRoleKey;

public interface UserToRoleMapper {
    int deleteByPrimaryKey(UserToRoleKey key);

    int insert(UserToRoleKey record);

    int insertSelective(UserToRoleKey record);
}