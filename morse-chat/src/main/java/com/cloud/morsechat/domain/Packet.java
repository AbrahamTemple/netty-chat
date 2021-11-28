package com.cloud.morsechat.domain;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Packet implements Serializable {

    private String me;

    private String code;

    private String friend;

    private String content;

    private String type;

    public Packet(String friend, String content) {
        this.friend = friend;
        this.content = content;
    }

    public Packet(String me, String code, String type) {
        this.me = me;
        this.code = code;
        this.type = type;
    }

    public Packet(String me, String friend, String content, String type) {
        this.me = me;
        this.friend = friend;
        this.content = content;
        this.type = type;
    }
}
