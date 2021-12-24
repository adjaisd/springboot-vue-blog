package com.adjaisd.service.impl;

import com.adjaisd.entity.Comment;
import com.adjaisd.mapper.CommentMapper;
import com.adjaisd.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
