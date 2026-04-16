package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果 类
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result <T>{
    private Integer code; //0成功 1失败
    private String message;//提示信息
    private T data;

     public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }
    public static Result success() {
        return new Result(0, "操作成功", null);
    }
    public static Result errors(String message) {
        return new Result(1, message, null);
    }

}
