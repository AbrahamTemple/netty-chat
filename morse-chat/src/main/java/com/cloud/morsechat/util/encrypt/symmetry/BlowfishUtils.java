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
 * Blowfish工具类
 * Blowfish：密钥长（4至56）*8，块长64，速度快，在安全界尚未被充分分析、论证
 * @author duanxinyuan
 * 2019/2/15 19:16
 */
public class BlowfishUtils {

    static {
        //导入Provider，BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Blowfish加密（最常用方式之一，使用Blowfish/ECB/PKCS5Padding方式，无偏移量）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是4~56位
     */
    public static String encrypt(String data, String key) {
        return encrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * Blowfish加密（最常用方式之一，使用Blowfish/ECB/PKCS5Padding方式，无偏移量）
     * @param data 密文
     * @param key 密钥，长度必须是4~56位
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * Blowfish加密（最常用方式之一，使用Blowfish/CBC/PKCS7Padding方式）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是4~56位
     * @param iv 偏移量，长度必须是8位
     */
    public static String encrypt(String data, String key, String iv) {
        return encrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * Blowfish加密（最常用方式之一，使用Blowfish/CBC/PKCS7Padding方式）
     * @param data 密文
     * @param key 密钥，长度必须是4~56位
     * @param iv 偏移量，长度必须是8位
     */
    public static byte[] encrypt(byte[] data, byte[] key, String iv) {
        return encrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * Blowfish加密（不带偏移量）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是4~56位
     */
    public static String encrypt(String data, String key, Mode mode, Padding padding) {
        return encrypt(data, key, null, mode, padding);
    }

    /**
     * Blowfish加密（不带偏移量）
     * @param data 密文
     * @param key 密钥，长度必须是4~56位
     * @return 密文（Base64编码）
     */
    public static byte[] encrypt(byte[] data, byte[] key, Mode mode, Padding padding) {
        return encrypt(data, key, null, mode, padding);
    }

    /**
     * Blowfish加密
     * @param data 明文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是4~56位
     * @param iv 偏移量，长度必须是8位
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
     * Blowfish加密
     * @param data 明文
     * @param key 密钥，长度必须是4~56位
     * @param iv 偏移量，长度必须是8位
     * @param mode 密码块工作模式
     * @param padding 填充方式
     * @return 密文
     */
    public static byte[] encrypt(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        check(data, key, iv, mode, padding);
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            String algorithm = Algorithm.getAlgorithm(Algorithm.Blowfish, mode, padding);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);
            // 初始化
            if (StringUtils.isNotEmpty(iv)) {
                AlgorithmParameters parameters = AlgorithmParameters.getInstance(Algorithm.Blowfish.getAlgorithm());
                parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameters);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            }
            //加密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CipherException("Blowfish encrypt error", e);
        }
    }

    /**
     * Blowfish解密（最常用方式之一，使用Blowfish/ECB/PKCS5Padding方式，无偏移量）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是4~56位
     * @return 明文
     */
    public static String decrypt(String data, String key) {
        return decrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * Blowfish解密（最常用方式之一，使用Blowfish/ECB/PKCS5Padding方式，无偏移量）
     * @param data 密文
     * @param key 密钥，长度必须是4~56位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * Blowfish解密（最常用方式之一，使用Blowfish/CBC/PKCS7Padding方式）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是4~56位
     * @param iv 偏移量，长度必须是8位
     * @return 明文
     */
    public static String decrypt(String data, String key, String iv) {
        return decrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * Blowfish解密（最常用方式之一，使用Blowfish/CBC/PKCS7Padding方式）
     * @param data 密文
     * @param key 密钥，长度必须是4~56位
     * @param iv 偏移量，长度必须是8位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, String iv) {
        return decrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * Blowfish解密（不带偏移量）
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是4~56位
     * @return 明文
     */
    public static String decrypt(String data, String key, Mode mode, Padding padding) {
        return decrypt(data, key, null, mode, padding);
    }

    /**
     * Blowfish解密（不带偏移量）
     * @param data 密文
     * @param key 密钥，长度必须是4~56位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, Mode mode, Padding padding) {
        return decrypt(data, key, null, mode, padding);
    }

    /**
     * Blowfish解密
     * @param data 密文（Base64编码）
     * @param key 密钥（Base64编码），长度必须是4~56位
     * @param iv 偏移量，长度必须是8位
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
     * Blowfish解密
     * @param data 密文
     * @param key 密钥，长度必须是4~56位
     * @param iv 偏移量，长度必须是8位
     * @param mode 密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        check(data, key, iv, mode, padding);
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            String algorithm = Algorithm.getAlgorithm(Algorithm.Blowfish, mode, padding);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);
            // 初始化
            if (StringUtils.isNotEmpty(iv)) {
                AlgorithmParameters parameters = AlgorithmParameters.getInstance(Algorithm.Blowfish.getAlgorithm());
                parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameters);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            }
            //解密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CipherException("Blowfish decrypt error", e);
        }
    }

    /**
     * 生成Blowfish的Key（128位）
     * @return 密钥
     */
    public static byte[] generateKey() {
        return generateKey(128);
    }

    /**
     * 生成Blowfish的Key
     * @param length 密钥长度，可以选 （4至56）*8
     * @return 密钥
     */
    public static byte[] generateKey(int length) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm.Blowfish.getAlgorithm());
            keyGenerator.init(length);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new CipherException("Blowfish key generate error", e);
        }
    }

    private static SecretKeySpec getSecretKeySpec(byte[] key) {
        return new SecretKeySpec(key, Algorithm.Blowfish.getAlgorithm());
    }

    private static void check(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        checkKey(key);
        checkModeAndPadding(data, mode, padding);
        if (StringUtils.isNotEmpty(iv)) {
            checkIv(iv);
            if (mode == Mode.ECB) {
                throw new CipherException("Blowfish ECB mode does not use an IV");
            }
        }
    }

    /**
     * 校验Blowfish密码块工作模式和填充模式
     */
    private static void checkModeAndPadding(byte[] data, Mode mode, Padding padding) {
        if (mode == Mode.NONE) {
            throw new CipherException("invalid Blowfish mode");
        }
        if (padding == Padding.SSL3Padding || padding == Padding.PKCS1Padding) {
            throw new CipherException("invalid Blowfish padding");
        }
        boolean is8NotSupport = padding == Padding.NoPadding && (mode == Mode.ECB || mode == Mode.CBC) && data.length % 8 != 0;
        if (is8NotSupport) {
            throw new CipherException("data length must be multiple of 8 bytes on ECB/NoPadding or CBC/NoPadding mode");
        }
    }

    /**
     * 校验Blowfish密钥，长度必须是4~56位
     */
    private static void checkKey(byte[] key) {
        if (key == null) {
            throw new CipherException("Blowfish key cannot be null");
        }
        if (key.length < 4 || key.length > 56) {
            throw new CipherException("Blowfish key not 4~56 bytes long");
        }
    }

    /**
     * 校验Blowfish偏移量，长度必须是8位
     */
    private static void checkIv(String iv) {
        if (iv.length() != 8) {
            throw new CipherException("Blowfish iv not 8 bytes long");
        }
    }

}
