package com.oldking.user.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.oldking.exception.LoginException;
import com.oldking.response.ResponseCode;
import com.oldking.user.config.ApplicationYml;
import com.oldking.user.utils.JwtUtil;
import com.oldking.user.utils.LoginBean;
import com.oldking.user.utils.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private ApplicationYml applicationYml;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (ignoreUrl(request.getRequestURI())) {
            return true;
        }
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new LoginException(ResponseCode.LOGIN_ERROR);
        }
        LoginBean loginBean;
        try {
            loginBean = JwtUtil.decodeLogin(token);
        } catch (JWTVerificationException e) {
            log.error("invalid token:{},cause:{}", token, e.getMessage());
            return false;
        }
        LoginUtil.setLogin(loginBean);
        log.info(loginBean.toString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //清除登录用户信息，释放内存
        LoginUtil.remove();
    }

    private boolean ignoreUrl(String path) {
        List<String> paths = applicationYml.getPaths();
        if (CollectionUtils.isEmpty(paths)) {
            return false;
        }
        boolean flag = false;
        for (String s : paths) {
            if (antPathMatcher.match(s, path)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
