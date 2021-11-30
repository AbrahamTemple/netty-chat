package com.cloud.morsechat.service.rest.impl;

import com.cloud.morsechat.domain.GlobalKey;
import com.cloud.morsechat.entity.model.MosFriend;
import com.cloud.morsechat.service.rest.FriendService;
import com.cloud.morsechat.vo.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.12.1
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Service
public class FriendServiceImpl extends GlobalKey implements FriendService {

    @Resource
    com.cloud.morsechat.service.model.IFriendService IFriendService;

    @Override
    public RestResponse<List<MosFriend>> list(Long uid, HttpSession sess) {
        List<MosFriend> list = IFriendService.getWithByUsrId(uid);
        if(list != null){
            return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), list);
        }
        return new RestResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),null);
    }
}
