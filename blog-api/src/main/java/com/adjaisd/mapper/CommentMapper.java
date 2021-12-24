package com.adjaisd.mapper;

import com.adjaisd.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
