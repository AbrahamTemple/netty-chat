package com.cloud.morsechat.service.model.impl;

import com.cloud.morsechat.dao.FriendRepository;
import com.cloud.morsechat.dao.UserRepository;
import com.cloud.morsechat.entity.model.MosFriend;
import com.cloud.morsechat.entity.model.MosUser;
import com.cloud.morsechat.service.model.IFriendService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.28
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Service
public class IFriendServiceImpl implements IFriendService {

    @Resource
    FriendRepository friendRepository;

    @Resource
    UserRepository userRepository;

    @Override
    public FriendRepository mapper(){
        return friendRepository;
    }

    @Override
    public List<MosFriend> getWithByUsrId(Long uid) {
        return friendRepository.getAllByUserid(uid);
    }

    @Override
    public MosFriend save(MosFriend friend) {
        if(userRepository.findById(friend.getUserid()).isPresent()){ //isPresent()与isEmpty()相反
            return friendRepository.save(friend);
        }
        return null;
    }

}
