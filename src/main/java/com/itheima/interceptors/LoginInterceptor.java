package com.itheima.interceptors;

import com.itheima.pojo.Result;
import com.itheima.utils.ThreadLocalUtil;
import com.itheima.utils.jwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            System.out.println("拦截原因：token为空");
            return false;
        }

        try {
            Map<String,Object> claims = jwtUtil.parseToken(token);
            System.out.println("token验证成功，claims: " + claims);
            //把认证信息保存到threadLocal中
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            System.out.println("token验证失败，原因: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    //响应结束清除
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}

