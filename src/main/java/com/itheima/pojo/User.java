package com.itheima.pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    /**
     * 用户ID
     */
    @NotNull
    private Integer id;

    /**
     * 用户名
     */

    private String username;

    /**
     * 密码
     */
    @JsonIgnore //让springboot在转换json的时候忽略该字段
    private String password;

    /**
     * 昵称
     */
    @NotEmpty//表示不能为空
    @Pattern( regexp = "^\\S{1,10}$")//表示只能输入1-10个非空字符
    private String nickname ;

    /**
     * 邮箱
     */
    @NotEmpty//表示不能为空
    @Email//表示必须是邮箱格式
    private String email ;

    /**
     * 头像
     */
    private String userPic;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    
}
