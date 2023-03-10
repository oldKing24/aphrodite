package com.oldking.user.utils;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.oldking.exception.BaseException;
import com.oldking.user.config.QiNiuYunConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangzhiyong
 */
@Slf4j
@Component
public class FileUtil {
    private UploadManager uploadManager;

    private UploadManager bigFileUploadManager;
    @Autowired
    private QiNiuYunConfig qiNiuYunConfig;

    @PostConstruct
    public void init() {
        Configuration cfg = new Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        uploadManager = new UploadManager(cfg);
        Configuration bigFileCfg = new Configuration(Region.region2());
        bigFileCfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        bigFileCfg.resumableUploadMaxConcurrentTaskCount = 2;  // 设置分片上传并发，1：采用同步上传；大于1：采用并发上传
        FileRecorder fileRecorder = null;
        try {
            fileRecorder = new FileRecorder(qiNiuYunConfig.getTmpDir());
        } catch (IOException e) {
            log.error("初始化失败,原因：{}", e.getMessage());
            throw new BaseException("初始化失败");
        }
        bigFileUploadManager = new UploadManager(bigFileCfg, fileRecorder);
    }

    public String uploadFile(File file) {
        try {
            String name = getPath(file.getName());
            FileInputStream fis = new FileInputStream(file);
            uploadInputStream(fis, name);
            return name;
        } catch (FileNotFoundException e) {
            throw new BaseException("文件不存在");
        }
    }

    public String uploadBigFile(String localFilePath, String fileName) {
        String token = getToken();
        //设置断点续传文件进度保存目录
        String path = getPath(fileName);
        try {
            Response response = bigFileUploadManager.put(localFilePath, path, token);
            return path;
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error("文件上传异常,{}", r.getInfo());
            throw new BaseException("文件上传异常" + r.getInfo());
        }
    }

    public String uploadFile(MultipartFile file) {
        try {
            String name = getPath(file.getOriginalFilename());
            uploadInputStream(file.getInputStream(), name);
            return name;
        } catch (IOException e) {
            throw new BaseException("文件不存在");
        }
    }

    public Response uploadByte(byte[] bytes, String key) {
        String token = getToken();
        try {
            Response response = uploadManager.put(bytes, key, token);
            //解析上传成功的结果
            DefaultPutRet putRet = JSONObject.parseObject(response.bodyString(), DefaultPutRet.class);
            log.info("上传结果：{}", putRet);
            return response;
        } catch (QiniuException ex) {
            throw new BaseException(ex.getMessage());
        }
    }

    public Response uploadInputStream(InputStream inputStream, String key) {
        String token = getToken();
        try {
            Response response = uploadManager.put(inputStream, key, token, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = JSONObject.parseObject(response.bodyString(), DefaultPutRet.class);
            log.info("上传结果：{}", putRet);
            return response;
        } catch (QiniuException ex) {
            throw new BaseException(ex.getMessage());
        }
    }

    public String getToken() {
        Auth auth = Auth.create(qiNiuYunConfig.getAccessKey(), qiNiuYunConfig.getSecretKey());
        String upToken = auth.uploadToken(qiNiuYunConfig.getBucket());
        return upToken;
    }

    public String getPath(String fileName) {
        LocalDateTime now = LocalDateTime.now();
        int month = now.getMonth().getValue();
        String timeStrap = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        return String.format("%s%s/%s%s.%s",
                now.getYear(), month < 10 ? "0" + month : month, timeStrap, Math.round(3F), fileName);
    }

    public Long downLoadHttpFile(String targetFile, String localFilePath) {
        long size = HttpUtil.downloadFile(targetFile, localFilePath);
        return size;
    }

    /**
     * 根据内容写入对应的文件路径
     *
     * @param content  文件内容
     * @param filePath 上传文件路径
     * @return 文件
     */
    public static File getUpdateFile(String content, String filePath) {
        Assert.notBlank(filePath, "文件路径不能为空");
        log.info("写入本地自定义分词文件地址：{}", filePath);
        byte[] buffer = new byte[1024];
        InputStream inputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            log.error("写入流失败,原因{}", e.getMessage());
            throw new IllegalArgumentException("写入流失败:" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable ignored) {

                }
            }
        }
        File file;
        FileOutputStream fos = null;
        try {
            byte[] getData = bos.toByteArray();
            // 创建临时文件,目录已存在
            file = new File(filePath);
            fos = new FileOutputStream(file);
            fos.write(getData);
        } catch (IOException e) {
            log.error("创建文件失败,原因{}", e.getMessage());
            throw new IllegalArgumentException("创建文件失败" + e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                bos.close();
            } catch (Throwable ignored) {

            }
        }
        return file;
    }

    public static void readFile() throws Exception {
        File file1 = new File("C:\\Users\\13911\\Desktop\\1.txt");
        File file2 = new File("C:\\Users\\13911\\Desktop\\2.txt");
        Set<String> result = new HashSet<>();
        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        while (true) {
            String s1 = br1.readLine();
            String s2 = br2.readLine();
            if (s1 == null) {
                if (s2 != null) {
                    result.add(s2);
                } else {
                    break;
                }
            }
            if (!s1.equalsIgnoreCase(s2)) {
                result.add(s1);
            }
        }
        result.forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        FileUtil fileUtil = new FileUtil();
        Long size = fileUtil.downLoadHttpFile("http://rr4qzku9r.hn-bkt.clouddn.com/202303/202303091120542433.course1678331979460.xlsx", "D:\\tmp\\course1678331979460.xlsx");
        System.out.println(size);
    }
}
