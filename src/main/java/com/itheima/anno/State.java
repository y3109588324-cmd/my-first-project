package com.itheima.anno;


import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.List;

//写法的注解
@Documented//文档注解
@Constraint(validatedBy = {StateValidation.class})//提供校验规则的类
@Target(ElementType.FIELD)//元注解,可以描述注解的元数据信息
@Retention(RetentionPolicy.RUNTIME)//标记那个时间段会被保留
public @interface State {
    //提供校验失败的提示信息
    String message() default "state参数的值只能是已发布或者草稿";
// z指定分组
    Class<?>[] groups() default {};
//负载 获取带State注解的加载信息
    Class<? extends Payload>[] payload() default {};
}
