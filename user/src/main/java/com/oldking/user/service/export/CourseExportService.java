package com.oldking.user.service.export;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONObject;
import com.oldking.exception.BaseException;
import com.oldking.response.PageBean;
import com.oldking.user.client.RocketMQProducer;
import com.oldking.user.config.QiNiuYunConfig;
import com.oldking.user.domain.PCourse;
import com.oldking.user.domain.excel.ECourse;
import com.oldking.user.enums.ExportTypeConstant;
import com.oldking.user.repository.CourseRepository;
import com.oldking.user.request.export.CourseExportRequest;
import com.oldking.user.response.Course;
import com.oldking.user.service.ExportTaskService;
import com.oldking.user.utils.ExcelUtil;
import com.oldking.user.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangzhiyong
 */
@Component(CourseExportService.BEAN_ID)
@Slf4j
public class CourseExportService implements ExportDispatcher<CourseExportRequest> {
    public static final String BEAN_ID = ExportTypeConstant.COURSE;

    @Autowired
    private RocketMQProducer rocketMQProducer;
    @Autowired
    private ExcelUtil excelUtil;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private QiNiuYunConfig qiNiuYunConfig;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private ExportTaskService exportTaskService;

    @Override
    public Long commitExportTask(String type, Long taskId, String extra) {
        rocketMQProducer.sendExportMsg("test", type, extra);
        return taskId;
    }

    @Override
    public void doExportJob(String type, Long taskId, String searchBody) {
        // 开始导出
        log.info("---异步执行课程导出---");
        String fileName = "course" + System.currentTimeMillis() + ".xlsx";
        String localFilePath = qiNiuYunConfig.getTmpDir() + "\\" + fileName;
        CourseExportRequest request = JSONObject.parseObject(searchBody, CourseExportRequest.class);
        long startIndex = 0;
        long row = 10000;
        WriteSheet writeSheet = new WriteSheet();
        ExcelWriter excelWriter = EasyExcelFactory.write(localFilePath).build();
        List<ECourse> eCourseList;
        PageBean<PCourse> page;
        try {
            while (true) {
                page = courseRepository.page(new Course(), startIndex, row, "id", "asc");
                if (page.getList().isEmpty()) {
                    break;
                }
                eCourseList = page.getList().stream().map(this::initExcel).collect(Collectors.toList());
                writeSheet.setSheetName(String.valueOf(startIndex / 10));
                excelWriter.write(eCourseList, writeSheet);
                startIndex++;
                eCourseList.clear();
            }
        } catch (Exception e) {
            log.error("导出临时文件出现异常，原因：【{}】", e.getMessage());
            exportTaskService.error(taskId, e.getMessage());
            return;
        } finally {
            excelWriter.finish();
        }
        log.info("---导出文件完成---");
        log.info("---开始上传云---");
        // 上传七牛云
        String uploadFileUrl;
        try {
            uploadFileUrl = fileUtil.uploadBigFile(localFilePath, fileName);
        } catch (BaseException e) {
            log.error("上传文件出现异常，原因：【{}】", e.getMessage());
            exportTaskService.error(taskId, e.getMessage());
            return;
        }
        // 删除文件
        try {
            File file = new File(localFilePath);
            file.delete();
        } catch (Exception e) {
            log.error("源文件删除失败，原因{}", e.getMessage());
        }
        log.info("---上传云完成---");
        log.info("---异步执行课程导出完成---");
        // 更新成功任务
        exportTaskService.success(taskId, uploadFileUrl);
    }

    @Override
    public Long commitImportTask(String type, Long taskId, String filePath) {
        return null;
    }

    @Override
    public void doImportJob(String type, Long taskId) {

    }

    private ECourse initExcel(PCourse pCourse) {
        ECourse eCourse = new ECourse();
        eCourse.setId(pCourse.getId());
        eCourse.setName(pCourse.getName());
        eCourse.setContent(pCourse.getContent());
        eCourse.setType(pCourse.getType());
        eCourse.setStartTime(pCourse.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        eCourse.setEndTime(pCourse.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        eCourse.setTeacherId(pCourse.getTeacherId());
        eCourse.setImage(pCourse.getImage());
        eCourse.setUrl(pCourse.getUrl());
        return eCourse;
    }
}
