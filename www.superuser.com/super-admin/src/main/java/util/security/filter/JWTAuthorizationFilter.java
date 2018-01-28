package util.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import util.security.model.AuthSubject;
import util.security.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static util.security.SecurityConstants.HEADER;
import static util.security.SecurityConstants.TOKEN_PREFIX;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenService tokenService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager){

        super(authenticationManager);
        this.tokenService = new TokenService();
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest req, HttpServletResponse res, FilterChain chain
    ) throws IOException, ServletException{
        String token = getTokenFromHeader(req);
        if (token != null){
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(req, res);
    }

    private String getTokenFromHeader(HttpServletRequest req){
        String token = req.getHeader(HEADER);
        if (token == null || !token.startsWith(TOKEN_PREFIX)){
            return null;
        }
        return token;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        AuthSubject subject = tokenService.parseToken(token);
        if (subject == null){
            return null;
        }
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(
                subject.getUsername(), subject, authorities);
    }
}
