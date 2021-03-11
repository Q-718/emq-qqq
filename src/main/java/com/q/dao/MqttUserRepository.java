package com.q.dao;

import com.q.entity.MqttUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * jap mqtt_user 数据访问层
 * @Author: qyp
 * @Date: 2021/3/11 10:21
 * @Description:
 */
@Repository
public interface MqttUserRepository extends JpaRepository<MqttUser,Long> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    MqttUser  findByUsername(String username);
}
