package com.ggr;

import javax.jms.*;

/**
 * Created by GuiRunning on 2017/5/29.
 */
public class Reciever {


   public void revieveMessage(String subject){
       Connection connection = null;
       Session session = null;
       try {
           //1.获取连接
           connection = (Connection) MqConnutil.getConnection();
           connection.start();

           // 2.创建会话
           session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);


           Destination destination = session.createQueue(subject);

          MessageConsumer consumer = session.createConsumer(destination);

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

          // session.close();
          // connection.close();



       } catch (JMSException e) {
           e.printStackTrace();
           System.out.println("连接失败！");

       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    public static void main(String[] args){
        new Reciever().revieveMessage("tools.DEFAULT");
    }
}
