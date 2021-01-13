package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.util.EmailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Async
    public void sendVerifyCode(String code, String emailAddress) throws Exception {

        EmailSender.sendRegisterCode(String.valueOf(code), emailAddress);

    }
}
