package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增 分类

    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);
    //查询all
    @Select("select * from category where create_user=#{id}")
    List<Category> list(Integer id);
//根据id查询
     @Select("select * from category where id=#{id}")
    Category findById( Integer id);
     //更新
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")

    void update(Category category);
    //删除分类

    @Delete("delete from category where id=#{id}")
    void delete(Integer id);
}
