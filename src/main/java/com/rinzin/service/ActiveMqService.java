package com.rinzin.service;

import com.rinzin.entity.Student;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject:IntelliJ IDEA
 * @BelongsPackage:com.rinzin.service
 * @Author:Rinzin
 * @CreateTime:2020-05-22-21-17
 * @Decription:
 */
@Service
public class ActiveMqService {

    /**
     * JmsTemplate可以操作mq的方法，简单的封装，
     * jmsTemplate可以设置一些连接的属性，例如：
     * jmsTemplate.setTimeToLive();
     * jmsTemplate.setSessionAcknowledgeMode();
     * 等
     * 通过jmsTemplate获取连接工厂，实现直接操作ActiveMq
     * ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
     * Connection connection = connectionFactory.createConnection();
     * Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
     * Queue queue = session.createQueue("test");
     * MessageProducer producer = session.createProducer(queue);
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 对JmsTemplate进行封装，内部也是通过jmsTemplate进行消息的发送
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 没有指定发送的模式是Topic或者是Queue,默认发送的是Topic
     * @param destination
     * @param msg
     */
    public String send(String destination, String msg){
        String result = "OK";
        try {
            /**
             * destination如果不指定，pub-sub-domain设置为true的话，会创建Topic，设置为false,则创建queue,
             * 我们可以自己new一个Destination。
             * 可以看DynamicDestinationResolver这个类的resolveDestinationName方法
             */
            jmsMessagingTemplate.convertAndSend(destination,msg);
            //创建队列模式
            jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(destination),msg);
            //创建消息订阅模式
            jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(destination),msg);
        }catch (Exception e){
            result = "false";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送Queue消息
     * @param myQueue
     * @param message
     * @return
     */
    public String sendQueue(String myQueue, String message) {
        String result = "OK";
        Student student = new Student("rinzin", "13226304549", 18);
        try {
            jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(myQueue),student);
        }catch (Exception e){
            result = "false";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送Topic消息
     * @param myTopic
     * @param message
     * @return
     */
    public String sendTopic(String myTopic, String message) {
        String result = "OK";
        try {
            jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(myTopic),message);
        }catch (Exception e){
            result = "false";
            e.printStackTrace();
        }
        return result;
    }
}
