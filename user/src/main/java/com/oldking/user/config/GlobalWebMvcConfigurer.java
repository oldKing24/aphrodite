package com.oldking.user.config;

import com.oldking.user.handler.TraceIdInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangzhiyong
 */
@Component
@RequiredArgsConstructor
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {
    private final TraceIdInterceptor traceIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceIdInterceptor);
    }
}
