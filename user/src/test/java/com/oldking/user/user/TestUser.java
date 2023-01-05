package com.oldking.user.user;

import com.oldking.user.UserApplication;
import com.oldking.user.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhiyong
 */
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class TestUser {
    @Test
    public void test() {
        List<UserRequest> userList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            UserRequest user = new UserRequest();
            user.setUserName("1");
            user.setEmail("111");
            user.setPassword("111");
            user.setVerifyCode(111);
            userList.add(user);
            user.setEmail("222");
            System.out.println(user);
            user = new UserRequest();
            System.out.println(user);
        }
        for (UserRequest userRequest : userList) {
            System.out.println(userRequest);
        }
    }
}
