package com.cloud.morsechat.service.rest;

import com.cloud.morsechat.vo.RestResponse;

import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.29
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public interface UserService {
    RestResponse<Map<String, String>> login(String username, String password);
    RestResponse<Map<String, String>> info(String token);
    RestResponse<Map<String, String>> friend(String hash);
    RestResponse<Map<String, String>> strange(String nickname);
}
