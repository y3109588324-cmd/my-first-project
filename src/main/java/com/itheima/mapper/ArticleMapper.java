package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(title,content,create_time,update_time,create_user,state,cover_img,category_id) values(#{title},#{content},#{createTime},#{updateTime},#{createUser},#{state},#{coverImg},#{categoryId})")
    void add(Article article);
    //查询文章列表

    List<Article> list(Integer userId, Integer categoryId, String state);

    @Select("select * from article where id=#{id}")
    Article findById(Integer id);
@Update("update article set title=#{title},content=#{content},update_time=#{updateTime} where id=#{id}")
    void update(Article article);
    @Delete("delete from article where id= #{id}")
    void delete(Integer id);
}
