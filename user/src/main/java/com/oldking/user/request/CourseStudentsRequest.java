package com.oldking.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class CourseStudentsRequest {
    @NotNull(message = "courseId不能为空")
    private Long courseId;
    @NotEmpty(message = "studentIds不能为空")
    private List<Long> studentIds;
}
