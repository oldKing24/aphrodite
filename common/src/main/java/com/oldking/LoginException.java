package com.oldking;

import com.oldking.exception.BaseException;
import com.oldking.response.ResponseCode;

/**
 * @author wangzhiyong
 */
public class LoginException extends BaseException {
    public LoginException(ResponseCode responseCode) {
        super(responseCode);
    }
}
