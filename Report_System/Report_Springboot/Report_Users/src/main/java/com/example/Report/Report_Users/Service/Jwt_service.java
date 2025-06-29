package com.example.Report.Report_Users.Service;

import org.springframework.stereotype.Service;

import com.example.Report.Report_Users.Model.user_data;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class Jwt_service {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generatetoken(user_data user) {
       Map<String,Object>claims=new HashMap<>();
       claims.put("role", user.getRole());

       return Jwts.builder()
       .claims()
       .add(claims)
       .subject(user.getUsername())
       .issuedAt(new Date(System.currentTimeMillis()))
       .expiration(new Date(System.currentTimeMillis()+1000*60*60*10))
       .and()
       .signWith(getkey())
       .compact();
       
    }

    private SecretKey getkey() {
        byte[]keybytes=Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keybytes);
    }

    public String extractusername(String token) {
       Claims claims=extractany(token);
       return extractnames(claims);
    }

    private String extractnames(Claims claims) {
       return claims.getSubject();
    }

    public boolean validatetoken(String token, UserDetails userdetail) {

        String username =extractusername(token);
        return(userdetail.getUsername().equals(username)&&!istokenexpired(token));
    }

    private boolean istokenexpired(String token) {
      return extractexpiration(token).before(new Date());
    }

    private Date extractexpiration(String token) {
       Claims claim=extractany(token);
       return expireall(claim);
    }

    private Date expireall(Claims claim) {
       return claim.getExpiration();
    }

    public String extractrole(String token) {
       Claims claims=extractany(token);
       return ectractanyroles(claims);
    }

    private String ectractanyroles(Claims claims) {
        return claims.get("role", String.class);
    }

    public Claims extractany(String token){
        return Jwts.parser()
        .verifyWith(getkey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }




}
