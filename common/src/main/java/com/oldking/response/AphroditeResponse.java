package com.oldking.response;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class AphroditeResponse<T> {
    private static final String COMPONENT_ID = "DNS";

    private String componentId = COMPONENT_ID;
    private int code = ResponseCode.OK.getCode();
    private String msg = ResponseCode.OK.getMessage();
    private T data;
    private long total = 0L;
    private Boolean more;
    public boolean success;
    private String traceId;

    private Map<String, String> fields = new HashMap<>();

    public AphroditeResponse() {
    }

    public AphroditeResponse(T t) {
        this.data = t;
    }

    public AphroditeResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功
     */
    public static <T> AphroditeResponse<T> success() {
        AphroditeResponse<T> response = new AphroditeResponse<>();
        response.setSuccess(true);
        response.setTraceId(MDC.get("traceId"));
        return response;
    }

    /**
     * 成功
     */
    public static <T> AphroditeResponse<T> success(T data) {
        AphroditeResponse<T> response = new AphroditeResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTraceId(MDC.get("traceId"));
        return response;
    }

    /**
     * 成功
     */
    public static <T> AphroditeResponse<T> success(T data, long total) {
        AphroditeResponse<T> response = new AphroditeResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotal(total);
        response.setTraceId(MDC.get("traceId"));
        return response;
    }
}
