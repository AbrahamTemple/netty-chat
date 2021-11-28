package com.cloud.morsechat.service.rest;

import com.cloud.morsechat.domain.MessageBody;
import com.cloud.morsechat.domain.MessageBranch;
import com.cloud.morsechat.vo.RestResponse;

import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.29
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public interface MessageService {
    RestResponse<Boolean> save(MessageBody body);
    RestResponse<List<MessageBranch>> log(String hash1, String hash2);
}
