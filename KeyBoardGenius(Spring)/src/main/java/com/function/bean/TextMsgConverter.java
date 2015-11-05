package com.function.bean;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConverter;

import com.ibm.jms.JMSTextMessage;
/**
 * 本程序和MQ服务器之间消息的相互转化类.
 * 
 */
public class TextMsgConverter implements MessageConverter {

    /**
     * 转化本程序要发到MQ上的消息
     */
    public Message toMessage(Object obj, Session session) throws JMSException {
        JMSTextMessage objMsg = null;
        
        if (obj instanceof String) {
            objMsg = (JMSTextMessage)session.createTextMessage(obj.toString());
        }
        
        return objMsg;
    }

    /**
     * 转化MQ接受消息.
     */
    public String fromMessage(Message msg) throws JMSException {
        String message = null;
        
        if (msg instanceof TextMessage) {
            message = ((TextMessage)msg).getText();
        }
        
        return message;
    }
}
