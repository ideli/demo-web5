package com.meilele.common.shiro.dao;

import com.meilele.common.shiro.entity.RoleToResourceKey;

public interface RoleToResourceMapper {
    int deleteByPrimaryKey(RoleToResourceKey key);

    int insert(RoleToResourceKey record);

    int insertSelective(RoleToResourceKey record);
}