package com.oldking.user.file;

import com.google.gson.Gson;
import com.oldking.user.UserApplication;
import com.oldking.user.config.QiNiuYunConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangzhiyong
 */
@SpringBootTest(classes = UserApplication.class)
public class FileTest {
    @Autowired
    private QiNiuYunConfig qiNiuYunConfig;

    @Test
    public void test() {
        String accessKey = qiNiuYunConfig.getAccessKey();
        String secretKey = qiNiuYunConfig.getSecretKey();
        String bucket = "oldking-course";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //如果是Windows情况下，格式是 D:\\qiniu\\test.png

        String localFilePath = "C:\\Users\\13911\\Pictures\\Camera Roll\\java_basic.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

        String pattern = "YYYY-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            try {
                Response response = uploadManager.put(uploadBytes, sdf.format(new Date()), upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }


        try {
            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
            try {
                Response response = uploadManager.put(byteInputStream, sdf.format(new Date()), upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }
    }

    @Test
    public void testBigFile() {
//构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        cfg.resumableUploadMaxConcurrentTaskCount = 2;  // 设置分片上传并发，1：采用同步上传；大于1：采用并发上传
        //...其他参数参考类注释
        //...生成上传凭证，然后准备上传
        String accessKey = qiNiuYunConfig.getAccessKey();
        String secretKey = qiNiuYunConfig.getSecretKey();
        String bucket = qiNiuYunConfig.getBucket();
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\13911\\Desktop\\SunloginClient_12.0.1.40571_x64.exe";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "SunloginClient_12.0.1.40571_x64.exe";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
        System.out.println(localTempDir);
        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
            try {
                Response response = uploadManager.put(localFilePath, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
