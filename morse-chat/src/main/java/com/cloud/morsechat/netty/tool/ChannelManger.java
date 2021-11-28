package com.cloud.morsechat.netty.tool;

import com.alibaba.fastjson.JSON;
import com.cloud.morsechat.domain.MessageBranch;
import com.cloud.morsechat.domain.MessageBody;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
public class ChannelManger extends ChannelMangerAncestor{

    private static ReentrantReadWriteLock RWLOCK = new ReentrantReadWriteLock(true);

    private static ConcurrentMap<Channel, MessageBody> POOLS = new ConcurrentHashMap<>();

    private static ChannelManger INSTANCE;

    public static ChannelManger getInstance(){
        if(INSTANCE == null){
            synchronized (ChannelManger.class) {
                INSTANCE = new ChannelManger();
            }
        }
        return INSTANCE;
    }


    /**
     * 登录注册 channel
     */
    @Override
    public void addChannel(Channel channel, String token) {
        String remoteAddr = channel.id().asLongText(); //按自己需求解析地址
        //取出token的信息
        Map<String,String> visitor =  ParseToken(token);
        if(visitor != null){
            MessageBody master = new MessageBody(visitor.get(HASH),remoteAddr,channel);
            POOLS.put(channel, master);
        }
    }

    /**
     * 普通消息
     * @param myHash 发送者的Hash
     * @param friendHash 收件人的Hash
     * @param content 消息内容
     */
    @Override
    public void broadcastMessage(String myHash, String friendHash, String content, Integer type) {
        if (!StringUtils.isBlank(myHash)) {
            try {
                RWLOCK.readLock().lock();
                Set<Channel> keySet = POOLS.keySet();
                for (Channel ch : keySet) {
                    MessageBody master = POOLS.get(ch);
                    if (!master.getHash().equals(friendHash) ) continue;
                    MessageBranch slave = new MessageBranch(myHash, content, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), type);
                    master.setSlave(slave);
                    //这里写入Redis


                    String response = JSON.toJSONString(slave);
                    ch.writeAndFlush(new TextWebSocketFrame(response));
                }
            } finally {
                RWLOCK.readLock().unlock();
            }
        }
    }

    /**
     * 返回接收者信息
     * @param channel
     * @return
     */
    @Override
    public MessageBody getReceiver(Channel channel){
        return POOLS.get(channel);
    }

    /**
     * 注销 Channel
     */
    @Override
    public void removeChannel(Channel channel) {
        POOLS.remove(channel);
    }

}
