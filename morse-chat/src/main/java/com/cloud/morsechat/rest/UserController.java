package com.cloud.morsechat.rest;

import com.cloud.morsechat.aop.Permission;
import com.cloud.morsechat.service.rest.UserService;
import com.cloud.morsechat.vo.RestResponse;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(value = "user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping(value = "login")
    public RestResponse<Map<String, String>> login(@RequestParam String username, @RequestParam String password){
        return userService.login(username, password);
    }

    @GetMapping(value = "info/{token}")
    public RestResponse<Map<String, String>> info(@PathVariable String token){
        return userService.info(token);
    }

    @Permission
    @GetMapping(value = "friend")
    public RestResponse<Map<String, String>> friend(@RequestParam String hash){
        return userService.friend(hash);
    }

    @Permission
    @GetMapping(value = "strange")
    public RestResponse<Map<String, String>> strange(@RequestParam String nickname){
        return userService.strange(nickname);
    }
}
