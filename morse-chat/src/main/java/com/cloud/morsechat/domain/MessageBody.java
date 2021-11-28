package com.cloud.morsechat.domain;

import io.netty.channel.Channel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.9
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Data
@NoArgsConstructor
public class MessageBody implements Serializable {

    /**
     * 我：{朋友，消息，时间，类型}
     */

    private String hash; //登录令牌
    private String addr; // 地址
    private Channel channel;// 通道
    private MessageBranch slave; //消息对象

    public MessageBody(String hash, String addr, Channel channel) {
        this.hash = hash;
        this.addr = addr;
        this.channel = channel;
    }
}
