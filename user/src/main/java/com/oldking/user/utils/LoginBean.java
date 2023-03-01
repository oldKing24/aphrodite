package com.oldking.user.utils;

import lombok.Data;

@Data
public class LoginBean {
    private String userId;
    private String userName;
    private String userStatus;

    public LoginBean(String userId, String userName, String userStatus) {
        this.userId = userId;
        this.userName = userName;
        this.userStatus = userStatus;
    }
}
