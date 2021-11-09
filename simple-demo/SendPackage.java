package com.netty.websocket;

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
public class SendPackage implements Serializable {

    private String me;

    private String friend;

    private String content;

    public SendPackage(String friend, String content) {
        this.friend = friend;
        this.content = content;
    }
}
