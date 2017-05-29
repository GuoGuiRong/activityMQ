package com.ggr;

import javax.jms.*;

/**
 * Created by GuiRunning on 2017/5/29.
 */
public class Sender {

    /**
     * 演示如何向MQ发送消息，和JDBC操作数据库的过程很像
     *
     * 1.初始化连接工厂ConnectionFactory
     *
     * 2.创建连接Connection
     *
     * 3. 创建会话session
     *
     * 4.打开队列createQueue
     *
     * 5.获得消息生产者MessageProducer
     *
     * 6.使用消息生产者发送消息
     *
     * 7. 关闭会话session和连接Connection
     *
     * 可以看出，使用JMS发送一个这么简单的消息，需要这么多的步骤，不方便。
     *
     */
    public void SendMessage(String subject){
        Connection connection = null;
        Session session = null;
        try {
            //1.获取连接
            connection = (Connection) MqConnutil.getConnection();
            connection.start();

            // 2.创建会话
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);


            Destination destination = session.createQueue(subject);

            MessageProducer producer = session.createProducer(destination);

            TextMessage textMessage = session.createTextMessage();

            String s = "hello world,this is the first test";

            textMessage.setStringProperty("hello",s);

            producer.send(textMessage);

            session.commit();

            session.close();
            connection.close();



        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("连接失败！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Sender().SendMessage("tools.DEFAULT");
    }
}
