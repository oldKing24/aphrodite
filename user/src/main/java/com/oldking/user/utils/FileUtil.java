package com.oldking.user.utils;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangzhiyong
 */
@Slf4j
public class FileUtil {
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
        readFile();
    }
}
