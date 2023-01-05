package com.oldking.gateway;

import com.oldking.gateway.client.UserTokenFeign;
import com.oldking.response.AphroditeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wangzhiyong
 */
@Service
@Slf4j
public class AuthorizationService {
    @Autowired
    private UserTokenFeign tokenFeign;

    @Async
    public boolean auth(String token) {
        AphroditeResponse<Void> response = tokenFeign.valid(token);
        log.info(response.getMsg());
        return response.success;
    }
}
