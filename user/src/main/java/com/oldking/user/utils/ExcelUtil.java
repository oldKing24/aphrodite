package com.oldking.user.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author wangzhiyong
 */
@Component
@Slf4j
public class ExcelUtil {
    public <T> ExcelWriter exportExcel(HttpServletResponse response, Class<T> cls, String fileName) {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            return EasyExcel.write(response.getOutputStream(), cls).build();
        } catch (IOException e) {
            log.error("导出文件{}失败,原因：{}", fileName, e.getMessage());
            throw new IllegalArgumentException("");
        }
    }

    public <T> void importExcel(MultipartFile file, Class<T> cls, ReadListener<T> readListener) {
        try {
            EasyExcel.read(file.getInputStream(), cls, readListener).sheet().doRead();
        } catch (IOException e) {
            log.error("导入文件失败,原因：{}", e.getMessage());
        }
    }
}
