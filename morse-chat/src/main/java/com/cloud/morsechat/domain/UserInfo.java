package com.cloud.morsechat.domain;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.9
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Data
public class UserInfo {
    private String userId;  // UID
    private String addr;    // 地址
    private Channel channel;// 通道
}
