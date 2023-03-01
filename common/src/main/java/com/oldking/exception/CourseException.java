package com.oldking.exception;

import com.oldking.response.ResponseCode;

/**
 * @author wangzhiyong
 */
public class CourseException extends BaseException {
    public CourseException(ResponseCode responseCode) {
        super(responseCode);
    }
}
