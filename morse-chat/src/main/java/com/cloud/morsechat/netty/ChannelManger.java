package com.cloud.morsechat.netty;

import com.alibaba.fastjson.JSON;
import com.cloud.morsechat.domain.Packet;
import com.cloud.morsechat.domain.UserInfo;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.9
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public class ChannelManger {

    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    private static ConcurrentMap<Channel, UserInfo> infos = new ConcurrentHashMap<>();
    /**
     * 登录注册 channel
     */
    public static void addChannel(Channel channel, String uid) {
        String remoteAddr = channel.id().asLongText(); //按自己需求解析地址
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(uid);
        userInfo.setAddr(remoteAddr);
        userInfo.setChannel(channel);
        infos.put(channel, userInfo);
    }

    /**
     * 普通消息
     * @param friend
     */
    public static void broadcastMess(String me, String friend, String content) {
        if (!StringUtils.isBlank(friend)) {
            try {
                rwLock.readLock().lock();
                Set<Channel> keySet = infos.keySet();
                for (Channel ch : keySet) {
                    UserInfo userInfo = infos.get(ch);
                    if (!userInfo.getUserId().equals(me) ) continue;
                    Packet pkg = new Packet(friend, content);
                    String response = JSON.toJSONString(pkg);
                    ch.writeAndFlush(new TextWebSocketFrame(response));
                    /*  responseToClient(ch,friend);*/
                }
            } finally {
                rwLock.readLock().unlock();
            }
        }
    }

    /**
     * 返回接收者信息
     * @param channel
     * @return
     */
    public static UserInfo getReceiver(Channel channel){
        return infos.get(channel);
    }

    /**
     * 注销 Channel
     */
    public static void removeChannel(Channel channel) {
        infos.remove(channel);
    }

}
