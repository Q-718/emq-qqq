package com.q.mqtt.client;

/**
 *服务质量枚举
 * @Author: qyp
 * @Date: 2021/3/10 14:41
 * @Description:
 */
public enum QosEnum {
    Qos0(0),Qos1(1),Qos2(2);

    private final int value;

    QosEnum(int i) {
        this.value=i;
    }
    public int getValue(){
        return this.value;
    }
}
