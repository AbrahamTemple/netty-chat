package com.cloud.morsechat.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;


//public class JWTUtils {
//	private static String TOKEN ="token!DJNJAKWJEA531DA5W1";
//
//	public static String getToken(Map<String,String> map) {
//		JWTCreator.Builder build = JWT.create();
//		map.forEach((k,v)->{
//			build.withClaim(k,v);
//		});
//
//		return null;
//	}
//}

@Component
public class JWTUtils {

	private static final String SING = "6200ab3f-6558-431f-978f-32b4fca66ca0";// 密钥

	/**
	 * 生成token header.payload.sing
	 */
	public static String getToken(Map<String, String> map, Integer time) {
		// 设置令牌token过期时间
		Calendar instance = Calendar.getInstance();

		if(time == null || time == 0){
			instance.add(Calendar.HOUR, 1);// 默认一小时过期
		} else {
			instance.add(Calendar.HOUR, time);
		}


		// 创建jwt builder
		JWTCreator.Builder builder = JWT.create();

		// 遍历提供的键值对
		map.forEach(builder::withClaim);

//		builder.withClaim("userID", id);

		String token = builder.withExpiresAt(instance.getTime())// 指定令牌过期时间
				.sign(Algorithm.HMAC256(SING));// sign
		return token;
	}

	/**
	 * 验证token 合法性
	 * 并获取token信息
	 *
	 */
	public static DecodedJWT verify(String token) {
		return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
	}

	/**
	 * 获取token信息方法
	 */
	 public static DecodedJWT getTokenInfo(String token){
		DecodedJWT verify =JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
		return verify;
	}

}
