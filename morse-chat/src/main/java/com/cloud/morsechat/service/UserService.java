package com.cloud.morsechat.service;

import com.cloud.morsechat.dao.UserRepository;
import com.cloud.morsechat.entity.model.MosUser;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.28
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public interface UserService {
    UserRepository mapper();
    MosUser getByName(String username);
}
