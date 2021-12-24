package com.adjaisd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ms_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论
     */
    private String content;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 用户id
     */
    private Long authorId;

    /**
     * 文章id
     */
    private Long articleId;


}
