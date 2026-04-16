package com.itheima.pojo;

import lombok.Data;

import java.util.List;
@Data
public class PageBean< T> {
    private Long total;//总记录数
    private List< T> items;//当前页数据
}
