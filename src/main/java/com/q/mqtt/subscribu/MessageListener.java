package com.q.mqtt.subscribu;

import com.q.mqtt.client.EmqClient;
import com.q.mqtt.client.MessageCallBack;
import com.q.mqtt.client.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 *消息监听类
 * @Author: qyp
 * @Date: 2021/3/19 16:00
 * @Description:
 */
@Slf4j
@Configuration
public class MessageListener {

    @Autowired
    private EmqClient emqClient;

    @Autowired
    private MqttProperties mqttProperties;

    @Autowired
    private MessageCallBack messageCallBack;

    @EventListener({ContextRefreshedEvent.class})
    public void handleContextStart(ContextRefreshedEvent event) {
        subscribeTestTopic(event);

    }
    /**
     *订阅测试主题消息
     * @param event
     * @return void
     * @Author: qyp
     * @Date: 2021/3/19 16:02
     * @Description:
     */
    public void subscribeTestTopic(ContextRefreshedEvent event) {
        emqClient.connect(mqttProperties.getUsername(), mqttProperties.getPassword());
        emqClient.subscribe("testtopic/123",messageCallBack);

    }
}
