package com.encry.demo;

import com.encry.symmetry.AESUtils;
import com.encry.symmetry.DESUtils;
import com.encry.symmetry.DESedeUtils;
import com.encry.symmetry.RC4Utils;
import com.encry.util.Base64Util;

import javax.crypto.interfaces.PBEKey;
import java.util.*;
import java.util.regex.Pattern;


/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.9.19
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public class Base64Rc4Demo {

    public static void main(String[] args) throws Exception {
        String username = "Abraham";
        String key = UUID.nameUUIDFromBytes(username.getBytes()).toString();

        System.out.println("密钥："+key);

        System.out.println("你想说点什么：");
        Scanner scanner = new Scanner(System.in);


        //有无一个算法，不可解密，但是可以靠暴力破解解密
        //一定是多种不同的加密算法叠加而成的结果

        while (scanner.hasNextLine()){
            String say = scanner.nextLine();
            String ecry = Base64Util.encode(say);
            System.out.println("是否用base64加密过---"+isBase64Encode(ecry));
            ecry = RC4Utils.encryptOrDecrypt(ecry,key);
            System.out.println("rc4加密---"+ ecry);
            System.out.println("是否用base64加密过---"+isBase64Encode(ecry));
            String decry = RC4Utils.encryptOrDecrypt(ecry,key);
            System.out.println("rc4解密---"+ decry);
            System.out.println("base64解密---"+Base64Util.decode(decry));
        }

    }

    public static boolean isBase64Encode(String content){
        if(content.length()%4!=0){
            return false;
        }
        String pattern = "^[a-zA-Z0-9/+]*={0,2}$";
        return Pattern.matches(pattern, content);
    }

}
