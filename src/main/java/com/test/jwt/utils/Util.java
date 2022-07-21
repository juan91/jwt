package com.test.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class Util {

    private static final String SECRET_KEY = "ejkdrtb3478545io6gh89efhn3o4vnmceop;run349832";

    public String generarJwtToken(String idTransaccion, long expiracionMillis, String email,
                                  String usuario) {
        long ahoraMillis = System.currentTimeMillis();

        //Firmar el token con el Secreto
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

        //JWT Claims
        Map<String, String> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("Usuario", usuario);
        JwtBuilder builder = Jwts.builder().setId(idTransaccion)
                .setClaims(claims)
                .setIssuedAt(new Date(ahoraMillis))
                .signWith(signingKey, SignatureAlgorithm.HS256);

        if (expiracionMillis > 0) {
            Date exp = new Date(ahoraMillis + expiracionMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Claims decodificarJwt(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(jwt).getBody();
    }

}
