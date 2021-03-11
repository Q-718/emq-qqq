package com.q.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * emqx 回调类
 * @Author: qyp
 * @Date: 2021/3/10 14:30
 * @Description:
 */
@Component
public class MessageCallBack implements MqttCallback {

    public final  static Logger log= LoggerFactory.getLogger(MessageCallBack.class);
    /**
     * 丢失了对服务端的连接后触发的回调
     * @param cause
     */
    @Override
    public void connectionLost(Throwable cause) {
        // 资源的清理  重连
        log.info("丢失了对服务端的连接");
    }

    /**
     * 应用收到消息后触发的回调
     * @param topic
     * @param message
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("订阅者订阅到了消息,topic={},messageid={},qos={},payload={}",
                topic,
                message.getId(),
                message.getQos(),
                new String(message.getPayload()));
    }

    /**
     * 消息发布者消息发布完成产生的回调
     * @param token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        int messageId = token.getMessageId();
        String[] topics = token.getTopics();
        log.info("消息发布完成,messageid={},topics={}",messageId,topics);
    }
}
