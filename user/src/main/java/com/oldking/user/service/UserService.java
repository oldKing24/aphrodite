package com.oldking.user.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.oldking.user.config.SendEmailConfig;
import com.oldking.user.domain.PUser;
import com.oldking.user.enums.EmailSubjectEnum;
import com.oldking.user.enums.UserStatusEnum;
import com.oldking.user.repository.UserRepository;
import com.oldking.user.request.LoginRequest;
import com.oldking.user.request.SendCodeRequest;
import com.oldking.user.request.UserRequest;
import com.oldking.user.response.TokenResponse;
import com.oldking.user.utils.JwtUtil;
import com.oldking.user.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author wangzhiyong
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SendEmailConfig sendEmailConfig;
    @Autowired
    private RedisUtil redisUtil;

    @Transactional
    public Long save(UserRequest request) {
        validRegister(request);
        PUser user = new PUser();
        user.setName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        userRepository.save(user);
        return user.getId();
    }

    public TokenResponse login(LoginRequest request) {
        PUser pUser = validLogin(request);
        return JwtUtil.login(pUser);
    }

    public void sendCode(SendCodeRequest sendCodeRequest) {
        String key = String.format(RedisUtil.SEND_CODE_KEY, sendCodeRequest.getCode());
        validKey(key);
        int code = NumberUtil.generateRandomNumber(1000, 9999, 1)[0];
        try {
            String message = String.format("注册验证码%s，请勿泄露给他人", code);
            MailAccount account = new MailAccount();
            account.setHost(sendEmailConfig.getHost());
            account.setPort(sendEmailConfig.getPort());
            account.setAuth(true);
            account.setFrom(sendEmailConfig.getFrom());
            account.setUser(sendEmailConfig.getUser());
            account.setPass(sendEmailConfig.getPass());
            MailUtil.send(account, CollUtil.newArrayList(sendCodeRequest.getCode()),
                    EmailSubjectEnum.REGISTER.getDesc(), message, false);
        } catch (Exception e) {
            log.error("邮件发送失败，原因：{}", e.getMessage());
            throw new IllegalArgumentException("邮件发送失败，请重新发送");
        }
        redisUtil.set(key, code, sendEmailConfig.getExpireTime(), TimeUnit.MINUTES);
    }

    private void validRegister(UserRequest userRequest) {
        String key = String.format(RedisUtil.SEND_CODE_KEY, userRequest.getEmail());
        Integer code = redisUtil.get(key);
        if (code == null) {
            throw new IllegalArgumentException("验证码已失效，请重新发送");
        }
        if (!code.equals(userRequest.getVerifyCode())) {
            throw new IllegalArgumentException("验证码有误");
        }
        PUser userByEmail = userRepository.findByEmail(userRequest.getEmail());
        if (userByEmail != null) {
            throw new IllegalArgumentException("该邮件已被注册");
        }
        PUser userByName = userRepository.findByName(userRequest.getUserName());
        if (userByName != null) {
            throw new IllegalArgumentException("该用户名已被注册");
        }
    }

    private void validKey(String key) {
        Integer code = redisUtil.get(key);
        if (code != null) {
            throw new IllegalArgumentException("请勿重新发送");
        }
    }

    private PUser validLogin(LoginRequest loginRequest) {
        PUser byEmail = userRepository.findByEmail(loginRequest.getUserName());
        if (byEmail == null) {
            throw new IllegalArgumentException("邮箱不存在");
        }
        PUser pUser = userRepository.findByAccount(loginRequest.getUserName(), loginRequest.getPassword());
        if (pUser == null) {
            throw new IllegalArgumentException("密码不正确");
        }
        return pUser;
    }
}
