package com.encry.demo;

import com.encry.asymmetry.RSAUtils;
import com.encry.pojo.RSAKeyPair;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.9.20
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public class RsaDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        RSAKeyPair keyPair = RSAUtils.generateKey(); //获取密钥对
        String privateKey = keyPair.getPrivateKey();  //获取私钥
        String publicKey = keyPair.getPublicKey(); //获取公钥

        RSAPrivateKey rsaPriKey = RSAUtils.getPrivateKey(privateKey);
        RSAPublicKey rsaPubKey = RSAUtils.getPublicKey(publicKey);

        System.out.println("---私钥：\n"+privateKey);
        System.out.println("---公钥：\n"+publicKey);

        String str = "我是一个黑暗的大坏蛋";

        String encrypt = RSAUtils.encryptByPublicKey(str, publicKey); //公钥加密
        String decrypt = RSAUtils.decryptByPrivateKey(encrypt, privateKey); //私钥解密

        System.out.println("---加密结果：\n"+encrypt);
        System.out.println("---解密结果：\n"+decrypt);
    }

}
