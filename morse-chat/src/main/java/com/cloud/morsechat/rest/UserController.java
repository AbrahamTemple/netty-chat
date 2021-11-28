package com.cloud.morsechat.rest;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cloud.morsechat.entity.model.MosUser;
import com.cloud.morsechat.service.UserService;
import com.cloud.morsechat.util.JWTUtils;
import com.cloud.morsechat.util.RedisUtil;
import com.cloud.morsechat.util.encrypt.hash.MD5Utils;
import com.cloud.morsechat.vo.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.22
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Slf4j
@RestController
public class UserController {

    @Resource
    RedisUtil redisUtil;

    @Resource
    UserService userService;

    @GetMapping(value = "login")
    public RestResponse<Map<String, String>> login(@RequestParam String username, @RequestParam String password){

        MosUser user = userService.getByName(username);

        if(user != null){

            String slat = user.getSalt();

            if(MD5Utils.md5(password + slat).equals(user.getPassword())){


                Map<String, String> data = new HashMap<>();


                data.put("username",user.getUsername());
                data.put("nickname",user.getNickname());
                data.put("hash", user.getHash());
                data.put("avatar", user.getAvatar());
                data.put("sex", user.getSex()==1?"男生":user.getSex()==2?"女生":"私密");
                data.put("email", user.getEmail());
                data.put("content", user.getContent());

                String token = JWTUtils.getToken(data,1);

                data.put("token", token);

                log.info("令牌一个小时内生效");

                return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),data);
            }
        }
        return new RestResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),null);
    }

    @GetMapping(value = "info")
    public RestResponse<Map<String, String>> info(@RequestParam String token){
        Map<String, String> data = new HashMap<>();
        try {
            DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
            tokenInfo.getClaims().forEach((k,v) -> {
                data.put(k,v.asString());
            });
        } catch (Exception e){
            log.info("无效令牌");
            return new RestResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),null);
        }
        return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), data);
    }

}
