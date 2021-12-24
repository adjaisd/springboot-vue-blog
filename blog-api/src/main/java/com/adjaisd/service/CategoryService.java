package com.adjaisd.service;

import com.adjaisd.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */
public interface CategoryService extends IService<Category> {

    Category getCategoryByName(String categoryName);

    boolean addCategory(Category category);

}
