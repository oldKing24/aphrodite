package com.oldking.todo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhiyong
 */
public class ConverterFactory {

    private ConverterFactory() {

    }

    public static final Map<String, String> converter = new HashMap<>();

    static {
        converter.put("1", "2");
    }
}
