package com.oldking.exception;

import com.oldking.response.ResponseCode;

/**
 * @author wangzhiyong
 */
public class BaseException extends RuntimeException {
    private ResponseCode responseCode;

    public BaseException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
