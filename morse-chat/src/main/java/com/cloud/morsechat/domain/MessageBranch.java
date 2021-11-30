package com.cloud.morsechat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class MessageBranch implements Serializable {

    private String hash; //收件人hash
    private String nickname;
    private String avatar;
    private String content; //聊天内容
    private String createTime; //发送时间
    private Integer type; //消息类型

    public MessageBranch(String hash) {
        this.hash = hash;
    }

    public MessageBranch(String hash, String content, String createTime, Integer type) {
        this.hash = hash;
        this.content = content;
        this.createTime = createTime;
        this.type = type;
    }
}
