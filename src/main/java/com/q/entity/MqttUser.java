package com.q.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 *  mqtt 数据库用户实体类
 * @Author: qyp
 * @Date: 2021/3/11 10:11
 * @Description:
 */
@Data
@Entity
@Table(name = "mqtt_user")
public class MqttUser {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "mc_addr")
    private String mcAddr;

    @Column(name = "role")
    private String role;

    @Column(name = "creatTime")
    private Date creatTime;

}
