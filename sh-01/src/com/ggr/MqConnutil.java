package com.ggr;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * Created by GuiRunning on 2017/5/29.
 */


public class MqConnutil {

   private static String user = null;
   private static String password = null;
   private static String url = null;

    // 1. 初始化连接工厂
    private static ConnectionFactory contectionFactory = null;

   static{
       user =  ActiveMQConnection.DEFAULT_USER;
       password = ActiveMQConnection.DEFAULT_PASSWORD;
       url=ActiveMQConnection.DEFAULT_BROKER_URL;
       contectionFactory = new ActiveMQConnectionFactory(user, password, url);
   }


   public static javax.jms.Connection getConnection() throws JMSException {
       return  contectionFactory.createConnection();

   }

}
