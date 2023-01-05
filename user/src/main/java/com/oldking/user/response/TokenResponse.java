package com.oldking.user.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class TokenResponse {
    /**
     * 鉴权token
     */
    private String accessToken;
    /**
     * 刷新token
     */
    private String refreshToken;
}
