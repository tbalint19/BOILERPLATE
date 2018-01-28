package util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import util.security.filter.JWTAuthorizationFilter;

import static util.security.SecurityConstants.*;

@EnableWebSecurity
@ComponentScan(basePackages = "com")
public class WebSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()

                .antMatchers(PUBLIC_URLS).permitAll()

                .regexMatchers(FRONTEND_URLS).permitAll()

                .regexMatchers(STATIC_ASSETS).permitAll()

                .antMatchers(HttpMethod.OPTIONS).permitAll()

                .antMatchers(DEFAULT_LOGIN_URL).denyAll()

                .anyRequest().denyAll()

                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager));
    }
}
