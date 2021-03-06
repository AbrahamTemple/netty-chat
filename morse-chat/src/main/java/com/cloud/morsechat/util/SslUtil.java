package com.cloud.morsechat.util;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.12.6
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public class SslUtil {

    private static volatile SSLContext sslContext = null;

    // type是PKCS12、path是pfx文件路径、password是pfx对应的密码
    public static SSLContext createSSLContext(String type ,String path ,String password) throws Exception {
        if(null == sslContext){
            synchronized (SslUtil.class) {
                if(null == sslContext){
                    // 支持JKS、PKCS12
                    KeyStore ks = KeyStore.getInstance(type);
                    // 证书存放地址
                    InputStream ksInputStream = new FileInputStream(path);
                    ks.load(ksInputStream, password.toCharArray());
                    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    kmf.init(ks, password.toCharArray());
                    sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(kmf.getKeyManagers(), null, null);
                }
            }
        }
        return sslContext;
    }
}
