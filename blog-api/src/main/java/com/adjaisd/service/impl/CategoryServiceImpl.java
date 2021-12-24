package com.adjaisd.service.impl;

import com.adjaisd.entity.Category;
import com.adjaisd.entity.User;
import com.adjaisd.mapper.CategoryMapper;
import com.adjaisd.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Category getCategoryByName(String categoryName) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", categoryName);
        return categoryMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean addCategory(Category category) {
        int insert = categoryMapper.insert(category);
        return insert>0;
    }
}
