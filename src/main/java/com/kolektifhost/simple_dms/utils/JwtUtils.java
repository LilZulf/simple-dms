/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.Jwts.parser;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Extracts the username (subject) from the provided JWT token.
     *
     * @param token the JWT token from which the username is to be extracted
     * @return the username (subject) extracted from the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, claims -> claims.getSubject());
    }

    /**
     * Extracts the expiration date from the provided JWT token.
     *
     * @param token the JWT token from which the expiration date is to be extracted
     * @return the expiration date extracted from the token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the provided JWT token.
     * 
     * @param token the JWT token from which the claim is to be extracted
     * @param claimsResolver a function that takes the {@link Claims} object and returns the desired claim
     * @return the extracted claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Parses the provided JWT token and returns all of its claims.
     * 
     * @param token the JWT token to be parsed
     * @return a {@link Claims} object containing all of the claims of the token
     */
    private Claims extractAllClaims(String token) {
        return parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    /**
     * Determines if the provided JWT token has expired.
     *
     * @param token the JWT token to be checked
     * @return true if the token has expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates a JWT token for the specified username.
     *
     * @param username the username for which the token is to be generated
     * @return a JWT token string with the specified username as the subject
     *         and an expiration date 3 months from the current date
     */
    public String generateToken(String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.MONTH, 3); // Add 3 months
        Date expirationDate = calendar.getTime();

        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }

    /**
     * Determines if the provided JWT token is valid for the specified username.
     *
     * @param token     the JWT token to be validated
     * @param username  the username for which the token is to be validated
     * @return true if the token is valid for the specified username, false otherwise
     */
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
