package com.oldking.response;

import java.util.Collections;
import java.util.List;

/**
 * @author wangzhiyong
 */
public class PageBean<T> {
    private List<T> list;

    /**
     * 总的页数
     */
    private Long pages;
    /**
     * 总记录数
     */
    private Long total;

    public List<T> getList() {
        return list;
    }

    public Long getTotal() {
        return total;
    }

    public Long getPages() {
        return pages;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public PageBean(List<T> list, Long total, Long pages) {
        this.list = list;
        this.total = total;
        this.pages = pages;
    }

    public PageBean() {
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public static <T> PageBean<T> emptyBean() {
        return new PageBean<T>(Collections.emptyList(), 0L, 0L);
    }
}
