package com.cloud.morsechat.service.rest.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cloud.morsechat.domain.GlobalKey;
import com.cloud.morsechat.entity.model.MosUser;
import com.cloud.morsechat.service.model.IUserService;
import com.cloud.morsechat.service.rest.UserService;
import com.cloud.morsechat.util.JWTUtils;
import com.cloud.morsechat.util.encrypt.hash.MD5Utils;
import com.cloud.morsechat.vo.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.29
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl extends GlobalKey implements UserService {

    @Resource
    IUserService iUserService;

    @Override
    public RestResponse<Map<String, String>> login(String username, String password) {
        MosUser user = iUserService.getByName(username);

        if(user != null){

            String slat = user.getSalt();

            if(MD5Utils.md5(password + slat).equals(user.getPassword())){


                Map<String, String> data = new HashMap<>();

                data.put(ID,user.getId().toString());
                data.put(USERNAME,user.getUsername());
                data.put(NICKNAME,user.getNickname());
                data.put(HASH, user.getHash());
                data.put(AVATAR, user.getAvatar());
                data.put(SEX, user.getSex()==1?"男生":user.getSex()==2?"女生":"私密");
                data.put(EMAIL, user.getEmail());
                data.put(CONTENT, user.getContent());

                String token = JWTUtils.getToken(data,1);

                data.put(TOKEN, token);

                log.info("令牌一个小时内生效");

                return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),data);
            }
        }
        return new RestResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),null);
    }

    @Override
    public RestResponse<Map<String, String>> info(String token) {
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
        return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),data);
    }

    @Override
    public RestResponse<Map<String, String>> friend(String hash) {
        MosUser user = iUserService.getByHash(hash);
        if(user != null){
            Map<String, String> data = new HashMap<>();
            data.put(ID,user.getId().toString());
            data.put(NICKNAME,user.getNickname());
            data.put(AVATAR, user.getAvatar());
            data.put(SEX,user.getSex()==1?"男生":user.getSex()==2?"女生":"私密");
            data.put(CONTENT, user.getContent());

            return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),data);
        }
        return new RestResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),null);
    }

    @Override
    public RestResponse<Map<String, String>> strange(String nickname) {
        List<MosUser> users = iUserService.getByNickname(nickname);
        if(users.size()>0){
            Map<String, String> data = new HashMap<>();
            users.forEach(user->{
                data.put(ID,user.getId().toString());
                data.put(NICKNAME,user.getNickname());
                data.put(AVATAR, user.getAvatar());
                data.put(SEX,user.getSex()==1?"男生":user.getSex()==2?"女生":"私密");
                data.put(CONTENT, user.getContent());
            });
            return new RestResponse<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),data);
        }
        return new RestResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),null);
    }

}
