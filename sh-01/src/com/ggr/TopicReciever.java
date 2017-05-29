package com.ggr;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by GuiRunning on 2017/5/29.
 */
public class TopicReciever {
    /**
     * 演示如何从MQ接受消息，和发送差不多
     *
     * 1.初始化连接工厂ConnectionFactory
     *
     * 2.创建连接Connection
     *
     * 3. 创建会话session
     *
     * 4.打开队列createQueue
     *
     * 5.获得消息消费者MessageConsumer
     *
     * 6.使用MessageConsumer接受消息
     *
     * 7. 关闭会话session和连接Connection
     *
     */
    public void reciever(){

        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password,url);

        Connection connection = null;
        Session session = null;
        Destination destination = null;
        try {
             connection= connectionFactory.createConnection();
             connection.start();

             session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

             Topic topic = session.createTopic("MQ.TOPIC");
             MessageConsumer consumer = session.createConsumer(topic);


             consumer.setMessageListener(new MessageListener() {
                 @Override
                 public void onMessage(Message message) {
                     try {
                         System.out.println(message.getStringProperty("hello"));
                     } catch (JMSException e) {
                         e.printStackTrace();
                     }
                 }
             });

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            /*if(session!=null){
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }*/
        }

    }

    public static void main(String[] args){
        new TopicReciever().reciever();
        TopicReciever TopicReciever = new TopicReciever();
        TopicReciever.reciever();

    }
}
