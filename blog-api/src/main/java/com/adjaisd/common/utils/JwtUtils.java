package com.adjaisd.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtils {
    public static final long EXPIRE = 1000 * 60 * 60 * 24;  // 过期时间
    public static final String APP_SECRET = "testSpringbootBueBlogTest";

    public static String getJwtToken(String username, String password) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("adjaisd-blog")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))

                .claim("username", username)
                .claim("password", password)

                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    // 判断token是否存在和有效
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 根据request请求判断token是否存在与有效
    public static boolean checkToken(HttpServletRequest request) {
        return JwtUtils.checkToken(request.getHeader("token"));
    }

    // 根据token获取会话
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws =
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }
}
