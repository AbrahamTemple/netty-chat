package com.cloud.morsechat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.28
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Data
@AllArgsConstructor
public class MessageBranch implements Serializable {

    private String hash; //收件人hash
    private String content; //聊天内容
    private String createTime; //发送时间
    private Integer type; //消息类型

}
