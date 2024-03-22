package com.basic.niroj.backend_social_media.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;


public class JwtProvider {


 private SecretKey  key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());


 public String GenerateToken(Authentication auth)
 {



     String jwt= Jwts.builder().setIssuer("niroj").setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+600000)).claim("email",auth.getName()).signWith(key).compact();

        return null;
 }
}
