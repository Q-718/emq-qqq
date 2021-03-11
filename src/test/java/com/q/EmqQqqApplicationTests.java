package com.q;

import com.q.dao.MqttUserRepository;
import com.q.entity.MqttUser;
import com.q.mqtt.client.EmqClient;
import com.q.mqtt.client.QosEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class EmqQqqApplicationTests {

    @Autowired
   private MqttUserRepository repository;

    @Autowired
    private EmqClient emqClient;

    @Test
    void contextLoads() {
        MqttUser q=new MqttUser();
        q.setUsername("qiyupeng");
        q.setPassword("123456");
        q.setRole("viewer");
        q.setMcAddr("AB_SD_03_45_34_O0");
        q.setCreatTime(new Date());
        repository.save(q);
        System.out.println("需要查询的用户:"+repository.findByUsername("qqq"));
        System.out.println(repository.findAll());

    }
    @Test
    void connectTest(){
        emqClient.connect("admin","public");
        emqClient.publish("testtopic/456","sdadasdas", QosEnum.Qos0,false);


    }

}
