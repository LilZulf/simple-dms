/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kolektifhost.simple_dms.utils;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private final String jwtSecret = "Kolektifhost";
    private final int jwtExpirationMs = 86400000;

    // public String generateJwtToken(String username) {
    //     return Jwts.builder()
    //             .setSubject(username)
    //             .setIssuedAt(new Date())
    //             .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
    //             .signWith(SignatureAlgorithm.HS512, jwtSecret)
    //             .compact();
    // }

    // public String getUsernameFromJwtToken(String token) {
    //     return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    // }

    // public boolean validateJwtToken(String authToken) {
    //     try {
    //         Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    //         return true;
    //     } catch (JwtException e) {
    //         return false;
    //     }
    // }
}
