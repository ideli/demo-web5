package com.meilele.common.shiro.dao;

import java.util.List;
import java.util.Map;

import com.meilele.common.shiro.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String username);

    List<Map<String, Object>> selectRolesByUserName(String username);

    List<Map<String, Object>> selectPermissionsByUserName(String username);
}