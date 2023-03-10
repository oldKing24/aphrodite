package com.oldking.user.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.oldking.user.config.QiNiuYunConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author wangzhiyong
 */
@Component
@Slf4j
public class ExcelUtil {
    @Autowired
    private QiNiuYunConfig qiNiuYunConfig;

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

    public <T> void simpleWrite(String fileName, Class<T> cls, List<T> list, String sheetName) {
        //这里需要指定写用哪个class去读，然后写到第一个sheet，名字为模板然后文件流会自动关闭
        //如果这里想使用03则传入excelType参数即可
        EasyExcel.write(fileName, cls).sheet(sheetName).doWrite(list);
    }
}
