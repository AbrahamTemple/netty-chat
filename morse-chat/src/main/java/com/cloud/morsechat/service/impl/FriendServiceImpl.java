package com.cloud.morsechat.service.impl;

import com.cloud.morsechat.dao.FriendRepository;
import com.cloud.morsechat.entity.model.MosFriend;
import com.cloud.morsechat.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.28
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    FriendRepository friendRepository;

    @Override
    public FriendRepository mapper(){
        return friendRepository;
    }

}
