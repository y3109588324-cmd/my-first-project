package com.itheima.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Category {
    /**
     * 分类ID
     */
    //定义两个分组，一个分组用于添加，一个分组用于修改


    @NotNull(groups = Update.class)
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty(groups = {Add.class,Update.class})
    private String categoryName;

    /**
     * 分类别名
     */
    @NotEmpty(groups = {Add.class,Update.class})
    private String categoryAlias;

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

    public interface Add{

    }
    public interface Update{

    }
}
