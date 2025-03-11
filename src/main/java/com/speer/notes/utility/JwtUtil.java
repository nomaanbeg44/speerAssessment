//package com.speer.notes.utility;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtUtil {
//	
//	private final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey"; // Keep it secure
//
//    private final long EXPIRATION_TIME = 86400000; // 24 hours
//    
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//    
////    private Key getSigningKey() {
////    	
////    	//byte[] keyBytes = Decoders.BASE64.decode(secretKey);
////        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
////    }
//    
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//    
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    
//    private Claims extractAllClaims(String token) {
//    	return Jwts
//                .parser()
//                .setSigningKey(SECRET_KEY)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public boolean validateToken(String token) {
//        return !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//    
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 hours
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
////    public String extractUsername(String token) {
////        return Jwts.parser()
////                .setSigningKey(getSigningKey())
////                .build()
////                .parseClaimsJws(token)
////                .getBody()
////                .getSubject();
////    }
//
//}
