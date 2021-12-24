package com.adjaisd.service;

import com.adjaisd.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
public interface ArticleService extends IService<Article> {

    boolean existArticle(long id);

    Article getArticle(long id);

    int addArticle(Article article);
}
