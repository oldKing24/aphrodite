package com.oldking.exception;

import com.oldking.response.ResponseCode;

/**
 * @author wangzhiyong
 */
public class LoginException extends BaseException {
    public LoginException(ResponseCode responseCode) {
        super(responseCode);
    }
}
