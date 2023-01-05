package com.oldking.gateway.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.oldking.gateway.AuthorizationService;
import com.oldking.gateway.config.ApiIgnoreUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author wangzhiyong
 */
@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered {
    private static final String TOKEN_NAME = "TOKEN";
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private ApiIgnoreUrlConfig apiIgnoreUrlConfig;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String currentUrl = exchange.getRequest().getURI().getPath();
        //1:不需要认证的url,不过滤放行的url
        if (shouldSkip(currentUrl)) {
            log.info("==========已跳过url{}=====", currentUrl);
            return chain.filter(exchange);
        }
        String headerToken = exchange.getRequest().getHeaders().getFirst(TOKEN_NAME);
        if (StringUtils.isBlank(headerToken)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        // 此处用异步调用，否则会有异常。
        ExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        Future<Boolean> future = executorService.submit(() -> authorizationService.auth(headerToken));
        try {
            boolean auth = future.get();
            if (!auth) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    /**
     * 方法实现说明:不需要过滤的路径
     *
     * @param currentUrl 当前请求路径
     */
    private boolean shouldSkip(String currentUrl) {
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String skipPath : apiIgnoreUrlConfig.getIgnoreUrl()) {
            if (pathMatcher.match(skipPath, currentUrl)) {
                return true;
            }
        }
        return false;
    }

}
