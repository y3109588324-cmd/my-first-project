package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);
    //条件分页雷彪查询

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
    //查看文章详情
    Article findById(Integer id);
    //修改文章
    void update(Article article);

    void delete(Integer id);
}
