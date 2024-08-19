package com.ray.controller;

import com.ray.pojo.Result;
import com.ray.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    private Result upload(MultipartFile image) throws IOException{

        // 调用阿里云ODD工具类，将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(image);

        // 将上传完成的url返回，用于浏览器的显示
        return Result.success(url);
    }
}
