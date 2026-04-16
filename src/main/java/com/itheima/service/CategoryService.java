package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {
    //新增分类
    void add(Category category);
    //列表查询

    List<Category> list();
    //根据id出巡分类



    Category findById(Integer id);
    //修改分类

    void update(Category category);

    void delete(Integer id);
}
