package com.cloud.morsechat.domain;

import lombok.Data;
import lombok.Getter;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.29
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Getter
public abstract class GlobalKey {

    protected static final String ID = "id";
    protected static final String USERNAME = "username";
    protected static final String HASH = "hash";
    protected static final String NICKNAME = "nickname";
    protected static final String AVATAR = "avatar";
    protected static final String SEX = "sex";
    protected static final String EMAIL = "email";
    protected static final String CONTENT = "content";
    protected static final String TOKEN = "token";
}
