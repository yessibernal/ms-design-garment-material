package com.innter.msdesigngarmentmaterial.services.IMPL;

import com.innter.msdesigngarmentmaterial.dtos.RolDto;
import com.innter.msdesigngarmentmaterial.services.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static com.innter.msdesigngarmentmaterial.constants.UtilConstants.SCOPES;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {


    @Value("${token-public-key}")
    private String  tokenPublicKey;

    @Override
    public String extractUserName(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    @Override
    public boolean isTokenValid(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningPublicKey()).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.err.println(ex);
//            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.err.println(ex);
//            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.err.println(ex);
//            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.err.println(ex);
//            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.err.println(ex);
//            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RolDto> extractScopes(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        List<String> scopes = (List<String>) claims.get(SCOPES);
        return scopes.stream().map(s -> RolDto.builder().rolName(s).build()).collect(Collectors.toList());
    }

    @Override
    public LocalDateTime extractDate(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        long epoch = (int) claims.get("exp");
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private Claims extractAllClaims(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parserBuilder().setSigningKey(getSigningPublicKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private PublicKey getSigningPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicBytes = Base64.getDecoder().decode(tokenPublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
}
