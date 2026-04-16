package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.jwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public Result add(@RequestBody  @Validated(Article.add.class) Article  article){
        articleService.add(article);
        return Result.success();

    }
    @GetMapping
 public Result<PageBean<Article>> list(
         Integer pageNum,
         Integer pageSize,
         @RequestParam (required = false) Integer categoryId,//表示该参数是可选的，可以不传
         @RequestParam (required = false) String state //表示该参数是可选的，可以不传
    ){
      PageBean<Article> pageBean =  articleService.list(pageNum,pageSize,categoryId,state);
      return Result.success(pageBean);
 }
 //获取文章详情
    @GetMapping("/detail")
    public Result<Article> detail( Integer id){
        //先查询是否存在
        if(articleService.findById(id)==null){
            return Result.errors("文章不存在");
        }
        Article article = articleService.findById(id);
        return Result.success(article);
    }
    //更新文章
    @PutMapping
    public Result update(@RequestBody @Validated(Article.update.class) Article article){
        articleService.update(article);
        return Result.success();
    }
    //删除文章
    @DeleteMapping
    public Result delete(Integer id){
        //检验该id的分类下是

            articleService.delete(id);
            return Result.success();
    }




}
