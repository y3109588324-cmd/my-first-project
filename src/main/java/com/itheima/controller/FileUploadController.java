package com.itheima.controller;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    //上传逻辑
    @PostMapping("/upload")
    public Result< String> upload(MultipartFile file) throws IOException {
        //把文件内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        //保证文件名字唯一，使用uuid
        //
        String uuid = java.util.UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));//获取文件后缀
        file.transferTo(new File("D:\\桌面\\file\\"+uuid));
        return Result.success("url地址");
    }
}
