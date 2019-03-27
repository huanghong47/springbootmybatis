package cn.hhfarcry.springbootmybatis.common.security.jwt;

import cn.hhfarcry.springbootmybatis.common.redis.Constant;
import cn.hhfarcry.springbootmybatis.common.security.utils.Base64ConvertUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JAVA-JWT工具类
 * @author Wang926454
 * @date 2018/8/30 11:45
 */
@Component
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    //过期时间
    private static String accessTokenExpireTime;

    //密钥
    private static String encryptJWTKey;



    @Value("${accessTokenExpireTime}")
    public void setAccessTokenExpireTime(String accessTokenExpireTime) {
        JwtUtil.accessTokenExpireTime = accessTokenExpireTime;
    }

    @Value("${encryptJWTKey}")
    public void setEncryptJWTKey(String encryptJWTKey) {
        JwtUtil.encryptJWTKey = encryptJWTKey;
    }



    /**
     * 验证token
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, Constant.JWTACCOUNT) + Base64ConvertUtil.decode(encryptJWTKey);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        }catch (Exception e){
            LOGGER.error("JWTToken异常:" + e.getMessage());
        }
        return false;
    }

    /**
     * 获得Token中的信息无需secret解密也能获得,获取凭证
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            LOGGER.error("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
        return null;
    }

    /**
     * 生成签名
     * @param account
     * @param currentTimeMillis
     * @return
     */
    public static String sign(String account,String userId,String currentTimeMillis) {
        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64ConvertUtil.decode(encryptJWTKey);
            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpireTime) * 1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim(Constant.JWTACCOUNT, account)
                    .withClaim("userId",userId)
                    .withClaim(Constant.CURRENT_TIME_MILLIS, currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
        return null;
    }
}
