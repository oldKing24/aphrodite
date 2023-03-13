package com.oldking.user.service.export;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.oldking.user.domain.PCourse;
import com.oldking.user.domain.excel.ECourse;
import com.oldking.user.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangzhiyong
 */
@Slf4j
public class CourseExportListener implements ReadListener<ECourse> {
    private CourseRepository courseRepository;

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    private List<ECourse> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public CourseExportListener(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void invoke(ECourse data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        List<PCourse> list = cachedDataList.stream().map(this::initDto).collect(Collectors.toList());
        courseRepository.batchSave(list);
        log.info("存储数据库成功！");
    }

    private PCourse initDto(ECourse vo) {
        PCourse course = new PCourse();
        course.setName(vo.getName());
        course.setContent(vo.getContent());
        course.setType(vo.getType());
        course.setStartTime(LocalDateTime.now());
        course.setEndTime(LocalDateTime.now());
        course.setTeacherId(vo.getTeacherId());
        course.setImage(vo.getImage());
        course.setUrl(vo.getUrl());
        course.setCreateTime(LocalDateTime.now());
        course.setCreateUser(0L);
        course.setUpdateTime(LocalDateTime.now());
        course.setUpdateUser(0L);
        return course;
    }
}
