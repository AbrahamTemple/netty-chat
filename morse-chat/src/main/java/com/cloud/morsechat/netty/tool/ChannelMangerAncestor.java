package com.cloud.morsechat.netty.tool;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cloud.morsechat.domain.MessageBody;
import com.cloud.morsechat.util.JWTUtils;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.29
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public abstract class ChannelMangerAncestor {

    protected static final String USERNAME = "username";
    protected static final String HASH = "hash";
    protected static final String NICKNAME = "nickname";
    protected static final String SEX = "sex";
    protected static final String EMAIL = "email";
    protected static final String CONTENT = "content";

    public abstract void addChannel(Channel channel, String token);
    public abstract void broadcastMessage(String myHash, String friendHash, String content, Integer type);
    public abstract MessageBody getReceiver(Channel channel);
    public abstract void removeChannel(Channel channel);

    /**
     * 解析token里登陆者的信息
     * @param token 令牌
     * @return 令牌持有者的各种信息
     */
    protected static Map<String,String> ParseToken(String token){
        Map<String, String> data = new HashMap<>();
        try {
            DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
            tokenInfo.getClaims().forEach((k,v) -> {
                data.put(k,v.asString());
            });
        } catch (Exception e){
            return null;
        }
        return data;
    }
}
