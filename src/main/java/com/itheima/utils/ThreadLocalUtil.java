package com.itheima.utils;
public class ThreadLocalUtil {

    private static final ThreadLocal THREAD_LOCAL =  new ThreadLocal();
    //获取键值
    public static<T> T get() {
        return (T) THREAD_LOCAL.get();
    }
//存储键值
    public static void set(Object value) {
        THREAD_LOCAL.set( value);
    }
//清除键值防止线程内存泄漏
    public static void remove() {
        THREAD_LOCAL.remove();
    }


}