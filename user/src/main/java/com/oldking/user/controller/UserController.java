package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.response.PageBean;
import com.oldking.user.request.LoginRequest;
import com.oldking.user.request.SendCodeRequest;
import com.oldking.user.request.UserRequest;
import com.oldking.user.response.TokenResponse;
import com.oldking.user.response.User;
import com.oldking.user.service.UserService;
import com.oldking.user.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public AphroditeResponse<Long> register(@Valid @RequestBody UserRequest userRequest) {
        return AphroditeResponse.success(userService.register(userRequest));
    }

    @PostMapping("/sendCode")
    public AphroditeResponse<Void> sendCode(@Valid @RequestBody SendCodeRequest sendCodeRequest) {
        userService.sendCode(sendCodeRequest);
        return AphroditeResponse.success();
    }

    @GetMapping("/login")
    public AphroditeResponse<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return AphroditeResponse.success(userService.login(loginRequest));
    }

    @GetMapping("/refresh")
    public AphroditeResponse<String> refresh(@RequestParam("refreshToken") String refreshToken) {
        return AphroditeResponse.success(JwtUtil.createToken(refreshToken));
    }

    @GetMapping("/page")
    public AphroditeResponse<PageBean<User>> page(User user, long page, long rows, String sortField, String sortType) {
        PageBean<User> pageData = userService.page(user, page, rows, sortField, sortType);
        return AphroditeResponse.success(pageData);
    }
}
