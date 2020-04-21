package com.web.controller;

import com.util.FastDFS;
import com.util.Message;
import com.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/upload")
public class UploadController {
    private static String ip = null;

    static {
        InputStream is = null;
        BufferedReader br = null;

        is = UploadController.class.getClassLoader().getResourceAsStream("fdfs_client.conf");
        br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                String[] splits = line.split(" = ");
                if (splits[0].equals("tracker_server")) {
                    ip = splits[1].split(":")[0];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation("图片上传，返回图片的地址")
    @PostMapping("/image")
    public Message upload(
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        // 1. 获取文件流（文件名称，后缀，大小）
        String fileName = file.getOriginalFilename();
        String ext_name = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (ext_name.contains("?")) {
            ext_name = ext_name.substring(0, ext_name.indexOf("?"));
        }
        long fileSize = file.getSize();
        if(fileSize > 3145728) {
            return MessageUtil.error("文件大小不能超过了3M");
        }
        // 2. 将文件交给fastdfsutil上传到附件服务器（FASTDFS）
        String[] result = FastDFS.upload(file.getBytes(), ext_name);
        if (result != null && result.length > 1) {
            String erpGroupName = result[0];
            String erpFileName = result[1];
            //上传成功
            String path = "http://" + ip + "/" + erpGroupName + "/" + erpFileName;
//            System.out.println(path);
            return MessageUtil.success("图片上传成功", path);
        }
        return MessageUtil.error("图片上传失败");
    }
}
