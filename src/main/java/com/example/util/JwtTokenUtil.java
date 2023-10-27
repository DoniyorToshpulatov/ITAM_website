package com.example.util;

import com.example.dto.JwtDTO;
import com.example.dto.JwtEmailChangeDTO;
import com.example.enums.ProfileRole;
import io.jsonwebtoken.*;


import java.util.Date;

public class JwtTokenUtil {

    private static final int tokenLifeTime = 1000 * 3600 * 24*3; // 3-day
    private static final String secretKey = "mazgi";

    public static String encode(String username, ProfileRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("username", username);
        jwtBuilder.claim("role", role);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLifeTime)));
        jwtBuilder.setIssuer("kunuz test portali");
        return jwtBuilder.compact();
    }

    public static String encode(Integer profileId) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("id", profileId);
        int tokenLifeTime = 1000 * 3600 * 24; // 1-day
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLifeTime)));
        jwtBuilder.setIssuer("Mazgi");

        return jwtBuilder.compact();
    }

    public static String encode(Integer profileId, String email) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("id", profileId);
        jwtBuilder.claim("email",email);
        int tokenLifeTime = 1000 * 3600 * 24; // 1-day
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLifeTime)));
        jwtBuilder.setIssuer("Mazgi");

        return jwtBuilder.compact();
    }

    public static JwtDTO decode(String token) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        String username = (String) claims.get("username");

        String role = (String) claims.get("role");
        ProfileRole profileRole = ProfileRole.valueOf(role);

        return new JwtDTO(username, profileRole);
    }

    public static Integer decodeMailGetUserId(String token) {

        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        Integer id = (Integer) claims.get("id");



        return id;


    }

   public static JwtEmailChangeDTO decodeMailGetUserIdAndEmailAddress(String token) {

        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        Integer id = (Integer) claims.get("id");
        String email = (String) claims.get("email");


        return new JwtEmailChangeDTO(id,email);


    }
}
