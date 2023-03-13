package com.oldking.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oldking.user.domain.PCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangzhiyong
 */
@Component
public interface CourseMapper extends BaseMapper<PCourse> {
    List<PCourse> listCourse(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("studentId") Long studentId);

    boolean saveBatch(List<PCourse> list);
}
