package com.itheima.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.itheima.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    /**
     * 文章ID
     */
   @NotNull(groups = {update.class})
    private Integer id;

    /**
     * 文章标题
     */
    @NotEmpty(groups ={add.class} )
    @Pattern(regexp = "^\\S{1,10}$" ,groups = {add.class})
    private String title;

    /**
     * 文章内容
     */
    @NotEmpty(groups ={add.class})
    private String content;

    /**
     * 文章封面
     */
    @NotEmpty(groups ={add.class})
    @URL(groups ={add.class}, message = "文章封面地址格式不正确")
    private String coverImg;

    /**
     * 文章状态: 只能是[已发布] 或者 [草稿]
     */
    @State(groups ={add.class})
    private String state ;

    /**
     * 文章分类ID
     */
    @NotNull(groups ={add.class})
    private Integer categoryId;

    /**
     * 创建人ID
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//时间格式化
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//时间格式化
    private LocalDateTime updateTime;

    public  interface add{}
    public  interface update{}
}