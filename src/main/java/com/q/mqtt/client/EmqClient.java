package com.q.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.q.mqtt.client.QosEnum.Qos0;

/**
 *emqx 客户端
 * @Author: qyp
 * @Date: 2021/3/10 11:40
 * @Description:
 */
@Component
public class EmqClient {

    public static final Logger log= LoggerFactory.getLogger(EmqClient.class);


    private IMqttClient mqttClient;

    @Autowired
    private MqttProperties mqttProperties;

    @Autowired
    private MqttCallback mqttCallback;

    /**
     * 初始化客户端
     */
    @PostConstruct
    public void init(){
        MqttClientPersistence persistence=new MemoryPersistence();
        try {
            mqttClient=new MqttClient(mqttProperties.getBrokerUrl(),mqttProperties.getClientId(),persistence);
        } catch (MqttException e) {
            log.error("初始化客户端mqttClient对象失败,errormsg={},brokerUrl={},clientId={}",e.getMessage(),mqttProperties.getBrokerUrl(),mqttProperties.getClientId());
        }
    }

    /**
     * 连接mqtt Broker
     */
    public void connect(String username,String password){
        MqttConnectOptions options=new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setCleanSession(true);

        mqttClient.setCallback(mqttCallback);
        try {
            mqttClient.connect(options);
        } catch (MqttException e) {
        log.error("mqtt客户端连接服务端失败,失败原因{}",e.getMessage());
        }

    }

    /**
     * 断开连接
     */
    @PreDestroy
    public void disConnect(){
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            log.error("断开连接产生异常,异常信息{}",e.getMessage());
        }

    }


    /**
     * 重连
     */
    public void reConnect(){
        try {
            mqttClient.reconnect();
        } catch (MqttException e) {
            log.error("重连失败,失败原因{}",e.getMessage());
        }
    }

    /**
     * 发布消息
     * @param topic
     * @param msg
     * @param qos
     * @param retain
     */
    public void publish(String topic,String msg,QosEnum qos,boolean retain){
        MqttMessage message=new MqttMessage();
        message.setPayload(msg.getBytes());
        message.setQos(qos.getValue());
        message.setRetained(retain);

        try {
            mqttClient.publish(topic,message);
        } catch (MqttException e) {
            log.error("发布消息失败,errormsg={},topic={},msg={},qos={},retain={}",e.getMessage(),topic,msg,qos.getValue(),retain);
        }
    }

    /**
     * 订阅一个主题
     * @param topicFilter
     * @param qos
     */
    public void subscribe(String topicFilter,QosEnum qos){
        try {
            mqttClient.subscribe(topicFilter,qos.getValue());
        } catch (MqttException e) {
            log.error("订阅主题失败,errormsg={},topicFilter={},qos={}",e.getMessage(),topicFilter,qos.getValue());
        }
    }

    /**
     *订阅主题
     * @param topicFilter
     * @param mqttCallback
     * @return void
     * @Author: qyp
     * @Date: 2021/3/19 16:37
     * @Description:
     */
    public void subscribe(String topicFilter,MqttCallback mqttCallback){
        try {
            log.info("订阅主题为:"+topicFilter);
            mqttClient.subscribe(topicFilter);
            mqttClient.setCallback(mqttCallback);
        } catch (MqttException e) {
            log.error("订阅主题失败,errormsg={},topicFilter={},qos={}",e.getMessage());
        }
    }
    /**
     * 取消订阅
     * @param topicFilter
     */
    public void unSubscribe(String topicFilter){
        try {
            mqttClient.unsubscribe(topicFilter);
        } catch (MqttException e) {
            log.error("取消订阅失败,errormsg={},topicfiler={}",e.getMessage(),topicFilter);
        }
    }

}
