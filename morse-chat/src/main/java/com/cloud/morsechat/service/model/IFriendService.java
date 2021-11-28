package com.cloud.morsechat.service.model;

import com.cloud.morsechat.dao.FriendRepository;
import com.cloud.morsechat.entity.model.MosFriend;

import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.28
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public interface IFriendService extends IService<FriendRepository>{
    List<MosFriend> getWithByUsrId(Long uid);
}
