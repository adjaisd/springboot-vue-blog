package com.adjaisd.service.impl;

import com.adjaisd.entity.User;
import com.adjaisd.mapper.UserMapper;
import com.adjaisd.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean existUserByUsername(String username) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("account", username);
        List<User> users = userMapper.selectByMap(map);
        return users.size() > 0;
    }


    @Override
    public boolean existUserByNickname(String nickname) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", nickname);
        List<User> users = userMapper.selectByMap(map);
        return users.size() > 0;
    }

    public boolean existUser(String username, String password) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("account", username);
        map.put("password", password);
        List<User> users = userMapper.selectByMap(map);
        return users.size() > 0;
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getUserByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", username);
        return userMapper.selectOne(queryWrapper);
    }
}
