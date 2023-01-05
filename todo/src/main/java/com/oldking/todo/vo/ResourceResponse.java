package com.oldking.todo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Data
public class ResourceResponse<T> {
    private String type;
    private List<T> list;
}
