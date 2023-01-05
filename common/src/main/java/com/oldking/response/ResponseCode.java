package com.oldking.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzhiyong
 */

@Setter
@Getter
public class ResponseCode {
    private final int code;
    private final String message;

    public ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static final ResponseCode OK = new ResponseCode(2000, "成功");
    public static final ResponseCode PARAMETER_INVALID = new ResponseCode(10001, "参数有误");
    public static final ResponseCode TOKEN_INVALID = new ResponseCode(10002, "token失效");
    public static final ResponseCode SYSTEM_ERROR = new ResponseCode(10003, "系统异常");

}
