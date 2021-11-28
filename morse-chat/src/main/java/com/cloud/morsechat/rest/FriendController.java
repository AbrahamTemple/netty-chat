package com.cloud.morsechat.rest;

import com.cloud.morsechat.aop.Permission;
import com.cloud.morsechat.entity.model.MosFriend;
import com.cloud.morsechat.service.FriendService;
import com.cloud.morsechat.vo.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class FriendController {

    @Resource
    FriendService friendService;

    @Permission
    @GetMapping(value = "list")
    public RestResponse<List<MosFriend>> list(@RequestParam Integer uid){
        return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),friendService.mapper().findAll());
    }
}
