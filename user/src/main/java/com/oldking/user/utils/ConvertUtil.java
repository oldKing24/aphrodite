package com.oldking.user.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

    public static <T> T copy(Object source, Class<T> cls) {
        T t = BeanUtils.instantiate(cls);
        BeanUtils.copyProperties(source, t);
        return t;
    }

    public static <T, K> List<T> copyList(List<K> sources, Class<T> cls) {
        List<T> list = new ArrayList<>();
        for (K source : sources) {
            list.add(copy(source, cls));
        }
        return list;
    }
}
