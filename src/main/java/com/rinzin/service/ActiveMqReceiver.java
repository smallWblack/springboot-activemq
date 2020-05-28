package com.rinzin.service;

import com.rinzin.constant.ActiveMqQueue;
import com.rinzin.constant.ActiveMqTopic;
import com.rinzin.constant.ListenerContainer;
import com.rinzin.entity.Student;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * @BelongsProject:IntelliJ IDEA
 * @BelongsPackage:com.rinzin.service
 * @Author:Rinzin
 * @CreateTime:2020-05-22-21-54
 * @Decription:消费者
 */
@Service
public class ActiveMqReceiver {

    /**
     * 监听Topic为ActiveMqTopic.TEST_MYTOPIC的消息
     * @param msg
     */
    @JmsListener(destination = ActiveMqTopic.TEST_MYTOPIC,containerFactory = ListenerContainer.LISTENER_CONTAINER_TOPIC)
    public void receiverMsgByTopic(String msg){
        System.out.println(msg);
    }

    /**
     * 监听Topic为AActiveMqQueue.TEST_MYQUEUE的消息
     * @param message
     */
    @JmsListener(destination = ActiveMqQueue.TEST_MYQUEUE,containerFactory = ListenerContainer.LISTENER_CONTAINER_QUEUE)
    public void receiverMsgByQueue(Message message){
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());
            } else if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                Student student = (Student) objectMessage.getObject();
                System.out.println(student.toString());
            } else if (message instanceof BytesMessage) {
                BytesMessage bytesMessage = (BytesMessage) message;
                String str = bytesMessage.readUTF();
                System.out.println(str);
            } else if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                System.out.println(mapMessage);
                System.out.println("name:" + mapMessage.getString("name"));
                System.out.println("age:" + mapMessage.getInt("age"));
                System.out.println("price:" + mapMessage.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
