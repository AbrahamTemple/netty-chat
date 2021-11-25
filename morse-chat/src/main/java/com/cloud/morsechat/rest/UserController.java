package com.cloud.morsechat.rest;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cloud.morsechat.util.JWTUtils;
import com.cloud.morsechat.util.RedisUtil;
import com.cloud.morsechat.vo.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.22
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@RestController
public class UserController {

    @Resource
    RedisUtil redisUtil;

    @RequestMapping("/login")
    public RestResponse<String> login(@RequestParam String username, @RequestParam String password, HttpServletRequest req){

        String token = null;

        if(username.equals("admin") && password.equals("admin")){
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("username",username);
            tokenMap.put("password",password);
            tokenMap.put("timestamp", String.valueOf(new Date().getTime()));
            token = JWTUtils.getToken(tokenMap,1);
        }

        return new RestResponse<String>(HttpStatus.OK.value(), HttpStatus.OK.toString(),token);
    }

    @RequestMapping("/info")
    public RestResponse<Map<String, String>> info(@RequestParam String token){
        String data = null;
        Map<String, String> tokenMap = new HashMap<>();
        try {
            DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
            data = JSON.toJSONString(tokenInfo.getClaims());
            tokenInfo.getClaims().forEach((k,v) -> {
                tokenMap.put(k,v.asString());
            });
        } catch (Exception e){
            System.out.println("令牌错误"+e);
        }

        return new RestResponse<Map<String, String>>(HttpStatus.OK.value(), HttpStatus.OK.toString(),tokenMap);
    }
}
