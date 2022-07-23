package com.JJP.restapiserver.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 토큰 생성 및 토큰 유효성 검증을 위한 클래스
 * InitializingBean를 implement 하는 이유:
        빈을 생성하여 주입한 후, secret(키 값)을 Base64로 decode 하여 key 값에 할당해주기 위함
 */
@Component
public class TokenProvider implements InitializingBean {
    // TokenProvider 클래스의 로그를 읽어오겠다.
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long tokenValidityInMilliseconds;

    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key =  Keys.hmacShaKeyFor(keyBytes);
    }


    // Token 유효성 검증 != Token 이용하여 Authentication 객체 정보 읽기 전 Token이 유효한지를 먼저 판단한다.
    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("Wrong JWT signature"); // 잘못된 토큰 서명 (잘못된 서명으로 만들어진 토큰이다)
        } catch(ExpiredJwtException e) {
            logger.info("Expired Token"); // 만료된 토큰
        } catch(UnsupportedJwtException e) {
            logger.info("Unsupported token"); // 지원하지 않는 토큰이다. (= 우리가 취급하는 토큰이 아니다)
        } catch(IllegalArgumentException e) {
            logger.info("Wrong JWT token"); // 토큰 자체가 잘못 되었다. (=우리가 발급한 토큰이 아니다)
        }
        return false;
    }

    // Authentication 객체의 권한 정보를 이용하여 토큰을 생성
    public String createToken(Authentication authentication) {
        String authorities =
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        // Token으로 압축하기
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    // Token을 이용하여 Authentication 객체 정보 얻기
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()               // JwtParserBuilder
                .setSigningKey(key)
                .build()                       // JwtParser
                .parseClaimsJws(token)
                .getBody();                    // get information from JWT

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // Token으로 만들어진 클레임을 이용하여 유저 객체를 만든 뒤, Authentication 객체를 리턴한다.
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

}
