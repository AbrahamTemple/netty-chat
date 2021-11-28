package com.cloud.morsechat.service.model.impl;

import com.cloud.morsechat.dao.UserRepository;
import com.cloud.morsechat.entity.model.MosUser;
import com.cloud.morsechat.service.model.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.28
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    UserRepository userRepository;

    @Override
    public UserRepository mapper(){
        return userRepository;
    }

    @Override
    public MosUser getByName(String username) {
        return userRepository.findByUsername(username);
    }


}
