package com.cloud.morsechat.rest;

import com.cloud.morsechat.aop.Permission;
import com.cloud.morsechat.domain.LogRequestBody;
import com.cloud.morsechat.domain.MessageBranch;
import com.cloud.morsechat.domain.MessageBody;
import com.cloud.morsechat.service.rest.MessageService;
import com.cloud.morsechat.vo.RestResponse;
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
@RequestMapping(value = "asset")
public class MessageController {

    @Resource
    MessageService messageService;

    @Permission
    @PostMapping(value = "save")
    public RestResponse<Boolean> save(@RequestBody MessageBody body){
        return messageService.save(body);
    }

    @Permission
    @PostMapping(value = "log")
    public RestResponse<List<MessageBranch>> log(@RequestBody LogRequestBody log){
        return messageService.log(log.getHash1(), log.getHash2());
    }
}
