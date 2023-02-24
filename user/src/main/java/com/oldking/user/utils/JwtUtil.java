package com.oldking.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.oldking.user.domain.PUser;
import com.oldking.user.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author wangzhiyong
 */
@Slf4j
public class JwtUtil {
    private static Long EXPIRE_TIME = 3600L;
    private static String SECRET = "oldking-user";

    public static TokenResponse login(PUser pUser) {
        TokenResponse tokenResponse = new TokenResponse();
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + EXPIRE_TIME * 1000);
        Date refreshTokenExpireTime = new Date(now.getTime() + EXPIRE_TIME * 1000 * 10);
        String accessToken = genarateToken(expireTime, pUser);
        String refreshToken = genarateToken(refreshTokenExpireTime, pUser);
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return tokenResponse;
    }

    public static String createToken(String refreshToken) {
        validToken(refreshToken);
        Date now = new Date();
        Date tokenExpireTime = new Date(now.getTime() + EXPIRE_TIME * 1000);
        return genarateToken(tokenExpireTime, new PUser());
    }

    public static void validToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String email = jwt.getClaim("email").asString();
        } catch (JWTVerificationException exception) {
            log.error("非法token");
            throw new JWTVerificationException("非法token");
        }
    }

    private static String genarateToken(Date expireTime, PUser pUser) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withExpiresAt(expireTime)
                    .withClaim("email", pUser.getEmail())
                    .withClaim("mobile", pUser.getMobile())
                    .withClaim("userId", pUser.getId())
                    .withClaim("userStatus", pUser.getStatus())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error("创建token失败：{}", exception.getMessage());
        }
        return token;
    }
}
