package util.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.security.model.AuthSubject;

import java.util.Date;

import static util.security.SecurityConstants.TOKEN_PREFIX;

@Service
public class TokenService {

    @Value("${security.constant.secretKey:secret}")
    private String SECRET_KEY;

    @Value("${security.constant.tokenExpiration}")
    private Long EXPIRATION_TIME;

//    private Gson serializer = new Gson();

    public String createToken(AuthSubject subject){
//        String serializedSubject = serializer.toJson(subject);
        String token = Jwts.builder()
//                .setSubject(serializedSubject)
                .setExpiration(new Date(subject.getCreated().getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();
        return TOKEN_PREFIX + token;
    }

    public AuthSubject parseToken(String token){
        String serializedSubject = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
//      return serializer.fromJson(serializedSubject, AuthSubject.class);
        return null;
    }

}
