package com.react.pnld.authentication;

import com.react.pnld.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class LDAPAuthProvider implements AuthenticationProvider {

    @Autowired
    private LDAPServices ldapServices;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        List<GrantedAuthority> authorities;
        Object detail;
        String username, password;

        username = String.valueOf(authentication.getPrincipal());
        password = String.valueOf(authentication.getCredentials());
        authorities = new ArrayList<GrantedAuthority>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        if(!ldapServices.loginUser(username, password)){
            return null;
        }

        detail = new Admin(username, "admin@email.com", password);

        return new SimpleAuthentication(username,authorities, detail, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


}
