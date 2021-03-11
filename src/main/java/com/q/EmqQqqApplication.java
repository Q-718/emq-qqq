package com.q;

import com.q.mqtt.client.EmqClient;
import com.q.mqtt.client.MqttProperties;
import com.q.mqtt.client.QosEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class EmqQqqApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmqQqqApplication.class, args);
    }

    @Autowired
    private EmqClient emqClient;

    @Autowired
    private MqttProperties mqttProperties;

//    @PostConstruct
    public void init(){
        //连接服务器
        emqClient.connect(mqttProperties.getUsername(),mqttProperties.getPassword());
        //订阅一个主题
        emqClient.subscribe("testtopic/#", QosEnum.Qos2);
         //初始化一个线程去推送消息
        new Thread(()->{
            while (true){
                emqClient.publish("testtopic/123","qpqp"+LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                        QosEnum.Qos2,false);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
