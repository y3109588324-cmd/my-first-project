package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        //添加创建时间更新时间
        //创建者的id通过拦截器获取
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建pageBean对象用于封装数据
        PageBean<Article> pb=new PageBean<>();
        //开启分页查询PageHelper,需要导入对应坐标
        PageHelper.startPage(pageNum,pageSize); //开启分页功能,参数是当前页码和每页显示的条数
        //调用mapper查询
        //从拦截器中获取对应的用户id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> pageBean=articleMapper.list(userId, categoryId,state);
        //Page中提供了方法可以获取pageHelper分页查询后的数据
        Page< Article> p= (Page< Article>) pageBean;
        pb.setTotal(p.getTotal());
        pb.setItems(pageBean);
        return pb;
    }

    @Override
    public Article findById( Integer  id) {
        Article article = articleMapper.findById(id);
        return  article;
    }

    @Override
    public void update(Article article) {
        //根据文章id来修改文章
        //需要传入对应修改的文章信息
        //文章标题信息

        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
