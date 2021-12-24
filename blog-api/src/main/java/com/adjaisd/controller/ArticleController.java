package com.adjaisd.controller;


import com.adjaisd.common.DateTimeUtils;
import com.adjaisd.common.exception.VerifyCodeException;
import com.adjaisd.common.result.Result;
import com.adjaisd.common.result.ResultCode;
import com.adjaisd.common.utils.JwtUtils;
import com.adjaisd.common.utils.verify.IVerifyCodeGen;
import com.adjaisd.common.utils.verify.SimpleCharVerifyCodeGenImpl;
import com.adjaisd.common.utils.verify.VerifyCode;
import com.adjaisd.entity.Article;
import com.adjaisd.entity.Category;
import com.adjaisd.entity.User;
import com.adjaisd.service.ArticleService;
import com.adjaisd.service.CategoryService;
import com.adjaisd.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    // 写文章 post: /article/write
    @ApiOperation(value = "写文章")
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    @ResponseBody
    public Result writeArticle(HttpServletRequest request,
                               @RequestBody Map<String, String> articleMap) {
        String username = articleMap.get("username");
        String category_name = articleMap.get("categoryName");

        User user = userService.getUserByName(username);
        if (user == null) return Result.fail()
                .code(500)
                .message("请重新登陆");
        long author_id = Long.parseLong(articleMap.get("authorId"));
        if (author_id != user.getId()) return Result.fail()
                .code(500)
                .message("用户ID错误.");

        Category category = categoryService.getCategoryByName(category_name);
        if (category == null) {
            Category cate = new Category(null, category_name, "");
            categoryService.addCategory(cate);
            //     return Result.fail()
            //          .code(500)
            //          .message("类别不存在");
        }
        category = categoryService.getCategoryByName(category_name);
        Long category_id = category.getId();

        String content = articleMap.get("content");
        String summary = articleMap.get("summary");
        if ("".equals(summary)) {
            int len = Math.min(content.length(), 100);
            summary = content.substring(0, len) + "...";
        }
        String title = articleMap.get("title");
        long createDate = DateTimeUtils.getCurrentDate().getTime();
        Article article = new Article(null, title, summary, author_id,
                createDate, category_id, content);
        int i = articleService.addArticle(article);
        if (i > 0) return Result.succ()
                .code(200)
                .message("发表文章成功.");
        else return Result.fail().code(500).message("发表文章失败");
    }

    //分页查询 get: /article/list?pageNumber=1&pageSize=5&sort=desc
    @ApiOperation(value = "分页查询文章")
    @GetMapping("/list")
    @ResponseBody
    public Result listArticles(HttpServletRequest request) {
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String sort = request.getParameter("sort");
        QueryWrapper<Article> wrapper;
        if (sort.equals("desc")) wrapper =
                new QueryWrapper<Article>().select().orderByDesc("create_date");
        else wrapper =
                new QueryWrapper<Article>().select().orderByAsc("create_date");
        Page<Article> page = articleService.page(new Page<>(pageNumber,
                pageSize), wrapper);
        return Result.succ()
                .data("articles", page.getRecords())
                .message("查询文章列表成功");
    }

    //获取文章正文 get: /article/view/{id}
    @ApiOperation(value = "获取文章内容")
    @GetMapping("/view/{articleId}")
    @ResponseBody
    public Result getArticle(@PathVariable String articleId) {
        long id = Long.parseLong(articleId);

        boolean exist = articleService.existArticle(id);
        if (!exist) return Result.fail()
                .code(500)
                .message("文章不存在.");
        Article article = articleService.getArticle(id);

        return Result.succ()
                .data("article", article)
                .message("查询文章成功");
    }
}
