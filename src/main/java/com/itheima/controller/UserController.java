package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import com.itheima.utils.jwtUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    //请求方式是post

   @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
       //参数检验

           User u = userService.findByUsername(username);

           if (u == null) {
               //没有被占用
               // 注册
               userService.register(username, password);
               return Result.success();
           } else {
               //占用
               return Result.errors("用户名已经被占用");
           }
   }
   @PostMapping("/login")
   public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password){
       //根据用户名查询用户
       User loginUser = userService.findByUsername(username);
       //判断是否存在
       if(loginUser== null){
           return Result.errors("用户名不存在");
       }
       //密码是否正确，密码加密再做比较
       if(Md5Util.getMd5String(password).equals(loginUser.getPassword())){
           //登录成功
           Map<String,Object> claims = new java.util.HashMap<>();
           claims.put("id",loginUser.getId());
           claims.put("username",loginUser.getUsername());
           String token = jwtUtil.getToken( claims);
           return Result.success(token);

       }
       return Result.errors("密码错误");
   }

    //主页显示用户信息
    @GetMapping("/info")
    public Result<User> userInfo(){
//        Map<String, Object> map = jwtUtil.parseToken(token);
//        String  username =  (String)map.get("username");
        //从threadLocal中获取用户名
        //根据用户名查询用户
Map<String,Object> map= ThreadLocalUtil.get();
String username = (String)map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
       userService.update(user);
       return Result.success();


    }
    @PatchMapping("/updateAvatar")
    public Result UpdateAvatar( @RequestParam @URL String avatarUrl){
       userService.updateAvatar(avatarUrl);
       return Result.success();
    }


    @PatchMapping("/updatePwd")
    public Result updatePwd( @RequestBody Map<String,String> params){
       //校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        //调用StringUtils的haslength方法判断参数是否为空
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.errors("缺少必要参数");
        }
        //原蜜吗是否匹配
        //调用userService查询用户,和旧密码比对
        Map<String,Object> map= ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.findByUsername(username);
        //旧密码和密码是否一致
        if(!Md5Util.getMd5String(oldPwd).equals(user.getPassword())){
            return Result.errors("原密码错误");
        }
        //新密码和确认密码是否一致
        if(!newPwd.equals(rePwd)){
            return Result.errors("两次密码不一致");
        }
        //调用service来更新密码
        userService.updatePwd(newPwd);
       return Result.success();
    }
}

