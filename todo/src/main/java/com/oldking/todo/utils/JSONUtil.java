package com.oldking.todo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;

/**
 * @author yudaobin
 **/
@Slf4j
public class JSONUtil {

  private static final ThreadLocal<ObjectMapper> threadLocal = ThreadLocal.withInitial(() -> {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setTimeZone(TimeZone.getDefault());
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  });

  public static String safeToJson(Object object) throws RuntimeException {
    if (object == null) {
      return null;
    } else {
      try {
        return threadLocal.get().writeValueAsString(object);
      } catch (Exception e) {
        log.error("序列化错误", e);
        throw new RuntimeException(e);
      }
    }
  }

  public static <T> T safeToObject(InputStream inputStream, Class<T> clazz) throws Exception {
    Assert.notNull(clazz, "clazz");
    if (inputStream == null) {
      return null;
    } else {
      try {
        return threadLocal.get().readValue(inputStream, clazz);
      } catch (IOException e) {
        log.error("反序列化<{}>错误", clazz.getName(), e);
        throw new Exception(e);
      }
    }
  }

  public static <T> T safeToObject(String json, Class<T> clazz) throws Exception {
    Assert.notNull(clazz, "clazz");
    if (StringUtils.hasText(json)) {
      return null;
    } else {
      try {
        return threadLocal.get().readValue(json, clazz);
      } catch (IOException e) {
        log.error("字符串<{}>反序列化<{}>错误", json, clazz.getName(), e);
        throw new Exception(e);
      }
    }
  }

  public static String objectToJson(Object value) throws RuntimeException {
    if (value == null)
      return null;

    try {
      return threadLocal.get().writeValueAsString(value);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static <T> T jsonToObject(String json, Class<T> valueType) throws RuntimeException {
    if (StringUtils.hasText(json))
      return null;
    try {
      return threadLocal.get().readValue(json, valueType);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /** 对含有泛型的返回体反序列化的支持 */
  public static <T> T jsonToObject(String json, TypeReference<T> valueType)
      throws RuntimeException {
    if (StringUtils.hasText(json)) {
      return null;
    }

    try {
      return threadLocal.get().readValue(json, valueType);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static <T> List<T> jsonToArrayList(String json, Class<T> elementType)
      throws RuntimeException {
    if (StringUtils.hasText(json)) {
      return new ArrayList<T>();
    }

    try {
      return threadLocal.get().readValue(json,
          threadLocal.get().getTypeFactory().constructParametricType(ArrayList.class, elementType));
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static String sort(String json) throws Exception {
    if (json == null) {
      return null;
    }
    json = json.trim();
    try {
      if (json.startsWith("[")) {
        return threadLocal.get().writeValueAsString(sortArrayJson(json));
      } else if (json.startsWith("{")) {
        return threadLocal.get().writeValueAsString(sortMapJson(json));
      }
    } catch (IOException e) {
      log.error("排序<{}>错误", json, e);
    }
    return json;
  }

  public static LinkedHashMap<String, Object> sortMapJson(String json) throws Exception {
    if (StringUtils.hasText(json)) {
      return null;
    }
    try {
      HashMap<String, Object> hashMap = threadLocal.get().readValue(json, HashMap.class);
      return sortMap(hashMap);
    } catch (IOException e) {
      log.error("字符串<{}>反序列化MAP错误", json, e);
      throw new Exception(e);
    }
  }

  public static ArrayList<Object> sortArrayJson(String json) throws Exception {
    if (StringUtils.hasText(json)) {
      return null;
    }
    try {
      List<Object> list = threadLocal.get().readValue(json, List.class);
      return sortList(list);
    } catch (IOException e) {
      log.error("字符串<{}>反序列化LIST错误", json, e);
      throw new Exception(e);
    }
  }

  public static <T> T safeToObject(String json, TypeReference<T> typeReference) throws Exception {
    Assert.notNull(typeReference, "typeReference");
    if (com.alibaba.cloud.commons.lang.StringUtils.isEmpty(json)) {
      return null;
    } else {
      try {
        return threadLocal.get().readValue(json, typeReference);
      } catch (IOException e) {
        log.error("字符串<{}>反序列化<{}>错误", json, typeReference.getType().getTypeName(), e);
        throw new Exception(e);
      }
    }
  }

  private static LinkedHashMap<String, Object> sortMap(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    LinkedHashMap<String, Object> result = new LinkedHashMap<>();
    TreeSet<String> treeSet = new TreeSet<>(map.keySet());
    for (String key : treeSet) {
      Object o = map.get(key);
      if (o == null) {
        continue;
      } else if (o instanceof Map) {
        result.put(key, sortMap((Map) o));
      } else if (o instanceof List) {
        result.put(key, sortList((List) o));
      } else {
        result.put(key, o);
      }
    }
    return result;
  }

  private static ArrayList<Object> sortList(List<Object> list) {
    if (list == null) {
      return null;
    }
    ArrayList result = new ArrayList<>();
    for (Object o : list) {
      if (o == null) {
        continue;
      } else if (o instanceof Map) {
        result.add(sortMap((Map<String, Object>) o));
      } else if (o instanceof List) {
        result.add(sortList((List<Object>) o));
      } else {
        result.add(o);
      }
    }
    Collections.sort(result);
    return result;
  }

}
