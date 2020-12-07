package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.service.SpuService;
import com.atguigu.gmall.product.test.TestFdfs;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Daoliang Zhou
 * @create 2020-12-05 16:43
 */
@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class FileUploadApiController {
    @Autowired
    SpuService spuService;

    @RequestMapping("fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String url="http:192.168.200.128:8080/";

        String path = TestFdfs.class.getClassLoader().getResource("tracker.conf").getPath();
        ClientGlobal.init(path);

        //连接tracker
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        System.out.println(trackerServer);

        //连接storage
        StorageClient storageClient = new StorageClient(trackerServer,null);

        //上传文件
        String filenameExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String[] jpgs = storageClient.upload_file(multipartFile.getBytes(), filenameExtension, null);

        //返回url
        for (String jpg : jpgs) {
            url = url + "/" + jpg;
        }

        return Result.ok(url);
    }
}
