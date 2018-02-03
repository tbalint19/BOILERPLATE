package util.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.security.model.AuthEntity;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String hash(String rawPassword){
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public AuthEntity authenticate(AuthEntity authEntity, String rawPassword){
        if (authEntity == null){
            return null;
        }
        if (!isValid(rawPassword, authEntity.getPassword())){
            return null;
        }
        return authEntity;
    }

    private Boolean isValid(String rawPassword, String encryptedPassword){
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }

}
