package com.rinzin.controller;

import com.rinzin.constant.ActiveMqQueue;
import com.rinzin.constant.ActiveMqTopic;
import com.rinzin.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject:IntelliJ IDEA
 * @BelongsPackage:com.rinzin.controller
 * @Author:Rinzin
 * @CreateTime:2020-05-22-21-03
 * @Decription:ActiveMq测试Controller
 */
@RestController
@RequestMapping("act/")
public class ActiveMqController {

    @Autowired
    public ActiveMqService activeMqService;

    @RequestMapping("send")
    public String send(){
        return activeMqService.send(ActiveMqTopic.TEST_MYTOPIC,"hi-rinzin");
    }
    @RequestMapping("sendQueue")
    public String sendQueue(){
        return activeMqService.sendQueue(ActiveMqQueue.TEST_MYQUEUE,"message");
    }
    @RequestMapping("sendTopic")
    public String sendTopic(){
        return activeMqService.sendTopic(ActiveMqTopic.TEST_MYTOPIC,"message");
    }

}
