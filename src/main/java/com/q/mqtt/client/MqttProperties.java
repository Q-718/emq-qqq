package com.q.mqtt.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 实体属性类
 * @Author: qyp
 * @Date: 2021/3/10 13:47
 * @Description:
 */
@Configuration
@ConfigurationProperties("mqtt")
public class MqttProperties {

//    broker-url: tcp://192.168.126.129/:1883
//    client-id: emq-client
//    username: admin
//    password: public

    private String brokerUrl;

    private String clientId;

    private String username;

    private String password;

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokeUrl) {
        this.brokerUrl = brokeUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MqttProperties{" +
                "brokeUrl='" + brokerUrl + '\'' +
                ", clientId='" + clientId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


