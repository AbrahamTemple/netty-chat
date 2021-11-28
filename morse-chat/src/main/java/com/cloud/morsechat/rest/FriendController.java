package com.cloud.morsechat.rest;

import com.cloud.morsechat.aop.Permission;
import com.cloud.morsechat.entity.model.MosFriend;
import com.cloud.morsechat.service.model.IFriendService;
import com.cloud.morsechat.vo.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.28
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@RestController
@RequestMapping(value = "with")
public class FriendController {

    @Resource
    IFriendService IFriendService;

    @Permission
    @GetMapping(value = "list/{uid}")
    public RestResponse<List<MosFriend>> list(@PathVariable Long uid){
        return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), IFriendService.getWithByUsrId(uid));
    }
}
