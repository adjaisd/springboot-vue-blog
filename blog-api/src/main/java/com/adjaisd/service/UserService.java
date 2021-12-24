package com.adjaisd.service;

import com.adjaisd.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
public interface UserService extends IService<User> {

    boolean existUserByUsername(String username);

    boolean existUserByNickname(String nickname);

    boolean existUser(String username, String password);

    int addUser(User user);

    User getUserByName(String username);

}
