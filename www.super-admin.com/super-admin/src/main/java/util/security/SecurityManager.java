package util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.security.model.AuthEntity;
import util.security.model.AuthSubject;
import util.security.service.AuthService;
import util.security.service.TokenService;

@Service
public class SecurityManager {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    public void secure(AuthEntity entity) {
        entity.setPassword(authService.hash(entity.getPassword()));
    }

    public AuthEntity authenticate(AuthEntity entity, String password) {
        return authService.authenticate(entity, password);
    }

    public String authorize(AuthEntity entity) {
        return tokenService.createToken(new AuthSubject(entity));
    }
}
