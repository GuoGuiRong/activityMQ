package com.ggr;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by GuiRunning on 2017/5/29.
 */
public class TopicSender {
    /**
     * 1.初始化连接工程
     * 2.创建连接
     * 3.创建会话
     * 4.创建topic
     * 5.获得消息的生产者MessageProducer
     * 6.使用生产者发送消息
     * 7.关闭会话和连接
     *
     */
    public void sendMessage(String msg){
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;

        String subject = "MQ.TOPIC";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password,url);


        Connection connection = null;
        Session session = null;
        Destination destination = null;


        try {

            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            destination = session.createTopic(subject);

           MessageProducer messageProducer = session.createProducer(destination);

           TextMessage message = session.createTextMessage();
           message.setStringProperty("hello",msg);

            messageProducer.send(message);

            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new TopicSender().sendMessage("你好，我是GG");
    }
}
