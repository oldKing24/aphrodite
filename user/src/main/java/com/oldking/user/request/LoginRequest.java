package com.oldking.user.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
