package com.oldking.user.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.oldking.response.PageBean;
import com.oldking.user.config.SendEmailConfig;
import com.oldking.user.domain.PRole;
import com.oldking.user.domain.PUser;
import com.oldking.user.domain.PUserRole;
import com.oldking.user.enums.Constant;
import com.oldking.user.enums.EmailSubjectEnum;
import com.oldking.user.enums.UserStatusEnum;
import com.oldking.user.enums.UserTypeEnum;
import com.oldking.user.repository.RoleRepository;
import com.oldking.user.repository.UserRepository;
import com.oldking.user.repository.UserRoleRepository;
import com.oldking.user.request.LoginRequest;
import com.oldking.user.request.SendCodeRequest;
import com.oldking.user.request.UserRequest;
import com.oldking.user.response.Role;
import com.oldking.user.response.TokenResponse;
import com.oldking.user.response.User;
import com.oldking.user.utils.ConvertUtil;
import com.oldking.user.utils.JwtUtil;
import com.oldking.user.utils.Md5Util;
import com.oldking.user.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wangzhiyong
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private SendEmailConfig sendEmailConfig;
    @Autowired
    private RedisUtil redisUtil;

    @Transactional
    public Long register(UserRequest request) {
        //校验
        validRegister(request);
        //添加用户信息
        PUser user = ConvertUtil.copy(request, PUser.class);
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        user.setType(UserTypeEnum.STUDENT.getCode());
        user.setPassword(Md5Util.encodedPassword(user.getPassword()));
        userRepository.save(user);
        //添加默认学生角色
        PUserRole userRole = new PUserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(Constant.STUDENT_ROLE_ID);
        userRoleRepository.batchInsert(Collections.singletonList(userRole));
        return user.getId();
    }

    public TokenResponse login(LoginRequest request) {
        PUser pUser = validLogin(request);
        return JwtUtil.login(pUser);
    }

    public PageBean<User> page(User user, long page, long rows, String sortField, String sortType) {
        PageBean<PUser> pageBean = userRepository.page(user, page, rows, sortField, sortType);
        return new PageBean<>(pageBean.getList().stream().map(x -> ConvertUtil.copy(x, User.class))
                .collect(Collectors.toList()), pageBean.getTotal(), pageBean.getPages());
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
        PUser userByName = userRepository.findByName(userRequest.getName());
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
        String password = Md5Util.encodedPassword(loginRequest.getPassword());
        PUser pUser = userRepository.findByAccount(loginRequest.getEmail(), password);
        if (pUser == null) {
            throw new IllegalArgumentException("账号与密码不正确");
        }
        return pUser;
    }
}
