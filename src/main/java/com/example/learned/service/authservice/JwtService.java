package com.example.learned.service.authservice;

import com.example.learned.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    private static final String ACCESS_SECRET_KEY = "4E645267556B56703272357538782F4S3F4428472B4B6250655368566G597133";
    private static final String REFRESH_SECRET_KEY = "4E645A67556B587D03272357538782F413F428472B4B6250655368566D597145";
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 24; // 24 hours
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7 days
    public Long extractUserIdFromAccessToken(String token, boolean isAccessToken) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }

        try {
            Claims claims;
            if (isAccessToken) {
                claims = extractAllClaimsAccess(token);
            } else {
                claims = extractAllClaimsRefresh(token);
            }

            return claims.get("userId", Long.class);
        } catch (Exception e) {
            // Handle exceptions gracefully (e.g., log, return null)
            return null;
        }
    }
    public String extractUsernameAccess(String token) {
        return extractClaimAccess(token, Claims::getSubject);
    }
    public String extractUsernameRefresh(String token) {
        return extractClaimRefresh(token, Claims::getSubject);
    }

    public <T> T extractClaimAccess(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaimsAccess(token);
        return claimsResolver.apply(claims);
    }
    public <T> T extractClaimRefresh(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaimsRefresh(token);
        return claimsResolver.apply(claims);
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(new HashMap<>(),userDetails);
    }
    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(),userDetails);
    }
    public String generateAccessToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        UserEntity userEntity = (UserEntity) userDetails; // Cast UserDetails to UserEntity
        extraClaims.put("userId", userEntity.getId());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(getAccessSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        UserEntity userEntity = (UserEntity) userDetails; // Cast UserDetails to UserEntity
        extraClaims.put("userId", userEntity.getId());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(getRefreshSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isAccessTokenValid(String token,UserDetails userDetails) {
        final String username = extractUsernameAccess(token);
        return (username.equals(userDetails.getUsername())) && !isAccessTokenExpired(token);
    }
    public Boolean isRefreshTokenValid(String token,UserDetails userDetails) {
        final String username = extractUsernameRefresh(token);
        return (username.equals(userDetails.getUsername())) && !isRefreshTokenExpired(token);
    }

    private Boolean isAccessTokenExpired(String token) {
        return extractExpirationAccess(token).before(new Date());
    }
    private Boolean isRefreshTokenExpired(String token) {
        return extractExpirationRefresh(token).before(new Date());
    }

    private Date extractExpirationAccess(String token) {
        return extractClaimAccess(token, Claims::getExpiration);
    }
    private Date extractExpirationRefresh(String token) {
        return extractClaimRefresh(token, Claims::getExpiration);
    }

    private Claims extractAllClaimsAccess(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getAccessSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Claims extractAllClaimsRefresh(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getRefreshSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getAccessSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ACCESS_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Key getRefreshSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(REFRESH_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
