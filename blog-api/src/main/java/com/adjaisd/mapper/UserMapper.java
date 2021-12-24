package com.adjaisd.mapper;

import com.adjaisd.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
