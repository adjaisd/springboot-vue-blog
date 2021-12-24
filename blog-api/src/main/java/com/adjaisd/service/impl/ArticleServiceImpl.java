package com.adjaisd.service.impl;

import com.adjaisd.entity.Article;
import com.adjaisd.entity.User;
import com.adjaisd.mapper.ArticleMapper;
import com.adjaisd.service.ArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public boolean existArticle(long id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<Article> articles = articleMapper.selectByMap(map);
        return articles.size() > 0;
    }

    @Override
    public Article getArticle(long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public int addArticle(Article article) {
        return articleMapper.insert(article);
    }
}
