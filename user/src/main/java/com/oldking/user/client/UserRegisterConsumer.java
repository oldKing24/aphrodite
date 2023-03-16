package com.oldking.user.client;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.oldking.user.config.SendEmailConfig;
import com.oldking.user.domain.PUser;
import com.oldking.user.enums.EmailSubjectEnum;
import com.oldking.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component
@RocketMQMessageListener(topic = "userRegister", consumerGroup = "${rocketmq.consumer.userRegisterGroup}")
@Slf4j
public class UserRegisterConsumer implements RocketMQListener<Long> {
    @Autowired
    private SendEmailConfig sendEmailConfig;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onMessage(Long userId) {
        PUser user = userRepository.detail(userId);
        if (user == null) {
            return;
        }
        String message = "欢迎您注册本网站的账号";
        MailAccount account = new MailAccount();
        account.setHost(sendEmailConfig.getHost());
        account.setPort(sendEmailConfig.getPort());
        account.setAuth(true);
        account.setFrom(sendEmailConfig.getFrom());
        account.setUser(sendEmailConfig.getUser());
        account.setPass(sendEmailConfig.getPass());
        MailUtil.send(account, CollUtil.newArrayList(user.getEmail()),
                EmailSubjectEnum.WELCOME.getDesc(), message, false);
    }
}
