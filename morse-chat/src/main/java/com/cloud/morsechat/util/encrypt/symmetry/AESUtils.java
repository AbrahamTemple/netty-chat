package com.cloud.morsechat.util.encrypt.symmetry;

import com.cloud.morsechat.util.encrypt.constant.Algorithm;
import com.cloud.morsechat.util.encrypt.constant.Mode;
import com.cloud.morsechat.util.encrypt.constant.Padding;
import com.cloud.morsechat.util.encrypt.exception.CipherException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * AES工具类
 * 高级加密标准(Advanced Encryption Standard)，密钥长可以选 128, 192, 256，块长128，用来替代原先的DES，速度快，安全
 * 优点：AES具有比DES更好的安全性、效率、灵活性，在软件及硬件上都能快速地加解密，相对来说较易于实作，且只需要很少的存储器。
 * @author duanxinyuan
 * 2019/2/15 18:41
 */
public class AESUtils {

    static {
        //导入Provider，BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES加密（最常用方式之一，使用AES/ECB/PKCS5Padding方式，无偏移量）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是16或24或者32位
     */
    public static String encrypt(String data, String key) {
        return encrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * AES加密（最常用方式之一，使用AES/ECB/PKCS5Padding方式，无偏移量）
     * @param data 密文
     * @param key 密钥，长度必须是16或24或者32位
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * AES加密（最常用方式之一，使用AES/CBC/PKCS7Padding方式）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是16或24或者32位
     * @param iv 偏移量，长度必须为16位
     */
    public static String encrypt(String data, String key, String iv) {
        return encrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * AES加密（最常用方式之一，使用AES/CBC/PKCS7Padding方式）
     * @param data 密文
     * @param key 密钥，长度必须是16或24或者32位
     * @param iv 偏移量，长度必须为16位
     */
    public static byte[] encrypt(byte[] data, byte[] key, String iv) {
        return encrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * AES加密（不带偏移量）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是16或24或者32位
     */
    public static String encrypt(String data, String key, Mode mode, Padding padding) {
        return encrypt(data, key, null, mode, padding);
    }

    /**
     * AES加密（不带偏移量）
     * @param data 密文
     * @param key 密钥，长度必须是16或24或者32位
     * @return 密文（Base64编码）
     */
    public static byte[] encrypt(byte[] data, byte[] key, Mode mode, Padding padding) {
        return encrypt(data, key, null, mode, padding);
    }

    /**
     * AES加密
     * @param data 明文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是16或24或者32位
     * @param iv 偏移量，长度必须为16位
     * @param mode 密码块工作模式
     * @param padding 填充方式
     * @return 密文（Base64编码）
     */
    public static String encrypt(String data, String key, String iv, Mode mode, Padding padding) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        byte[] encrypt = encrypt(data.getBytes(), key.getBytes(), iv, mode, padding);
        return Base64.encodeBase64String(encrypt);
    }

    /**
     * AES加密
     * @param data 明文
     * @param key 密钥，长度必须是16或24或者32位
     * @param iv 偏移量，长度必须为16位
     * @param mode 密码块工作模式
     * @param padding 填充方式
     * @return 密文
     */
    public static byte[] encrypt(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        check(data, key, iv, mode, padding);
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            String algorithm = Algorithm.getAlgorithm(Algorithm.AES, mode, padding);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);
            // 初始化
            if (StringUtils.isNotEmpty(iv)) {
                AlgorithmParameters parameters = AlgorithmParameters.getInstance(Algorithm.AES.getAlgorithm());
                parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameters);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            }
            //加密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CipherException("AES encrypt error", e);
        }
    }

    /**
     * AES解密（最常用方式之一，使用AES/ECB/PKCS5Padding方式，无偏移量）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是16或24或者32位
     * @return 明文
     */
    public static String decrypt(String data, String key) {
        return decrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * AES解密（最常用方式之一，使用AES/ECB/PKCS5Padding方式，无偏移量）
     * @param data 密文
     * @param key 密钥，长度必须是16或24或者32位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * AES解密（最常用方式之一，使用AES/CBC/PKCS7Padding方式）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是16或24或者32位
     * @param iv 偏移量，长度必须为16位
     * @return 明文
     */
    public static String decrypt(String data, String key, String iv) {
        return decrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * AES解密（最常用方式之一，使用AES/CBC/PKCS7Padding方式）
     * @param data 密文
     * @param key 密钥，长度必须是16或24或者32位
     * @param iv 偏移量，长度必须为16位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, String iv) {
        return decrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * AES解密（不带偏移量）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是16或24或者32位
     * @return 明文
     */
    public static String decrypt(String data, String key, Mode mode, Padding padding) {
        return decrypt(data, key, null, mode, padding);
    }

    /**
     * AES解密（不带偏移量）
     * @param data 密文
     * @param key 密钥，长度必须是16或24或者32位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, Mode mode, Padding padding) {
        return decrypt(data, key, null, mode, padding);
    }

    /**
     * AES解密
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是16或24或者32位
     * @param iv 偏移量，长度必须为16位
     * @param mode 密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static String decrypt(String data, String key, String iv, Mode mode, Padding padding) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        byte[] decrypt = decrypt(Base64.decodeBase64(data), key.getBytes(), iv, mode, padding);
        return new String(decrypt);
    }

    /**
     * AES解密
     * @param data 密文
     * @param key 密钥，长度必须是16或24或者32位
     * @param iv 偏移量，长度必须为16位
     * @param mode 密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        check(data, key, iv, mode, padding);
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            String algorithm = Algorithm.getAlgorithm(Algorithm.AES, mode, padding);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);
            // 初始化
            if (StringUtils.isNotEmpty(iv)) {
                AlgorithmParameters parameters = AlgorithmParameters.getInstance(Algorithm.AES.getAlgorithm());
                parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameters);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            }
            //解密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CipherException("AES decrypt error", e);
        }
    }

    /**
     * 生成AES的Key（128位）
     * @return 密钥
     */
    public static byte[] generateKey() {
        return generateKey(128);
    }

    /**
     * 生成AES的Key
     * @param length 密钥长度，可以选 128, 192, 256
     * @return 密钥
     */
    public static byte[] generateKey(int length) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm.AES.getAlgorithm());
            keyGenerator.init(length);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new CipherException("AES key generate error", e);
        }
    }

    private static SecretKeySpec getSecretKeySpec(byte[] key) {
        return new SecretKeySpec(key, Algorithm.AES.getAlgorithm());
    }

    private static void check(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        checkKey(key);
        checkModeAndPadding(data, mode, padding);
        if (StringUtils.isNotEmpty(iv)) {
            checkIv(iv);
            if (mode == Mode.ECB) {
                throw new CipherException("AES ECB mode does not use an IV");
            }
        }
    }

    /**
     * 校验AES密码块工作模式和填充模式
     */
    private static void checkModeAndPadding(byte[] data, Mode mode, Padding padding) {
        if (mode == Mode.NONE) {
            throw new CipherException("invalid AES mode");
        }
        if (padding == Padding.SSL3Padding || padding == Padding.PKCS1Padding) {
            throw new CipherException("invalid AES padding");
        }
        boolean is16NotSupport = padding == Padding.NoPadding && (mode == Mode.ECB || mode == Mode.CBC) && data.length % 16 != 0;
        if (is16NotSupport) {
            throw new CipherException("data length must be multiple of 16 bytes on ECB/NoPadding or CBC/NoPadding mode");
        }
    }

    /**
     * 校验AES密钥，长度必须是16或24或者32位
     */
    private static void checkKey(byte[] key) {
        if (key == null) {
            throw new CipherException("AES key cannot be null");
        }
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new CipherException("AES key not 16/24/32 bytes long");
        }
    }

    /**
     * 校验AES偏移量，长度必须是16位
     */
    private static void checkIv(String iv) {
        if (iv.length() != 16) {
            throw new CipherException("AES iv not 16 bytes long");
        }
    }

}
