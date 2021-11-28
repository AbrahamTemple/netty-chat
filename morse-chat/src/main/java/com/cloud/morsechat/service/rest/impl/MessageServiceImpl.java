package com.cloud.morsechat.service.rest.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.morsechat.domain.MessageBody;
import com.cloud.morsechat.domain.MessageBranch;
import com.cloud.morsechat.service.rest.MessageService;
import com.cloud.morsechat.util.RedisUtil;
import com.cloud.morsechat.vo.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.29
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    RedisUtil redisUtil;

    @Override
    public RestResponse<Boolean> save(MessageBody body) {
        Object data;
        if((data = redisUtil.hget(body.getHash(), body.getSlave().getHash())) != null){
            List<MessageBranch> branches = JSON.parseArray(JSON.toJSONString(data), MessageBranch.class);
            branches.add(body.getSlave());
            boolean result = redisUtil.hset(body.getHash(), body.getSlave().getHash(),JSON.toJSON(branches),3);
            return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),result);
        } else {
            List<MessageBranch> branches = new LinkedList<>();
            branches.add(body.getSlave());
            boolean result = redisUtil.hset(body.getHash(), body.getSlave().getHash(),JSON.toJSON(branches), 3);
            return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),result);
        }
    }

    @Override
    public RestResponse<List<MessageBranch>> log(String hash1, String hash2) {
        Object data;
        if((data = redisUtil.hget(hash1, hash2)) != null){
            List<MessageBranch> branches = JSON.parseArray(JSON.toJSONString(data), MessageBranch.class);
            return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),branches);
        }
        return new RestResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),null);
    }
}
