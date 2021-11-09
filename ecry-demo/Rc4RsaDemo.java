package com.encry.demo;

import com.alibaba.fastjson.JSON;
import com.encry.asymmetry.RSAUtils;
import com.encry.hash.SHAUtils;
import com.encry.pojo.RSAKeyPair;
import com.encry.symmetry.RC4Utils;
import com.encry.util.Base64Util;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Scanner;
import java.util.UUID;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.3
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public class Rc4RsaDemo {

    private static RSAKeyPair keyPair = RSAUtils.generateKey(); //获取密钥对
    private static String privateKey = keyPair.getPrivateKey();  //获取私钥
    private static String publicKey = keyPair.getPublicKey(); //获取公钥

    public static void main(String[] args) {

        /**
         * 密钥要计算
         */
        int counter = 0;
        String key;
        while (true){

            String hash = SHAUtils.sha256("Abraham" + counter);

            if(hash.startsWith(getToughValue(3))){ //前三位是否都是0开头
                System.out.println("---计算正确，计算次数: " + counter + " ---hash: "+hash);
                key = hash;
                break;
            }

            counter++;

            System.out.println("---计算错误---hash: "+hash);
        }

        System.out.println("密钥：" + key);

        System.out.println("你想说点什么：");
        Scanner scanner = new Scanner(System.in);


        /**
         * 先RC4再Rsa
         */

        while (scanner.hasNextLine()) {
            String say = scanner.nextLine();
            String ecry = RC4Utils.encryptOrDecrypt(say, key);
            System.out.println("rc4加密---" + ecry);
            String encrypt = RSAUtils.encryptByPublicKey(ecry, publicKey); //公钥加密
            System.out.println("公钥加密---" + encrypt);
            String decrypt = RSAUtils.decryptByPrivateKey(encrypt, privateKey); //私钥解密
            System.out.println("私钥解密---" + decrypt);
            String decry = RC4Utils.encryptOrDecrypt(decrypt, key);
            System.out.println("rc4解密---" + decry);
        }

    }

    private static String getToughValue(int tough){

        StringBuilder value = new StringBuilder("0");

        for (int i = 0; i < tough - 1; i++) {
            value.append("0");
        }

        return value.toString();
    }
}
