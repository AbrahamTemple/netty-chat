package com.cloud.morsechat.util.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.9.19
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public class Base64Util {

    /**
     * 编码字符串
     * @param str
     * @return
     */
    public static String encode(String str){
        if(StringUtils.isEmpty(str)) return "";
        return Base64.encodeBase64String(str.getBytes());
    }

    /**
     * 解码字符串
     * @param str
     * @return
     */
    public static String decode(String str){
        if(StringUtils.isEmpty(str)) return "";
        return new String(Base64.decodeBase64(str));
    }
}
