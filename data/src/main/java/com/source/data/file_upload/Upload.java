package com.source.data.file_upload;

import com.pojo.RESULT.Result;
import com.utils.AliyunUtils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 公共文件上传
 */
@RestController
@Slf4j
public class Upload {
    @Autowired
    private AliOssUtil aliOssUtil;
    @PostMapping("/admin/common/upload")
    public Result fileUpload(MultipartFile file) throws IOException {   // 参数名要与 前端传递的文件类型名一致
        log.info("文件上传 : {}",file.getName());
        String filename = file.getOriginalFilename();   // 获取原始文件名
        String name = null;
        if (filename != null) {
             name = filename.substring(filename.lastIndexOf("."));    //获取文件拓展后缀 包含.
        }
        String FILE = UUID.randomUUID() + name; // 构建全新文件名
        String upload = aliOssUtil.upload(file.getBytes(), FILE);   // 上传到阿里云oss
        return Result.success(upload);  // 将文件路径返回给前端去访问
    }
}
