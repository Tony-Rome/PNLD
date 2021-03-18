package com.react.pnld.authentication;

import com.react.pnld.model.Admin;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class LDAPAuthProvider implements AuthenticationProvider {

    private LDAPServices ldapServices = new LDAPServices();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        List<GrantedAuthority> authorities;
        Object detail;
        String nombre, clave;

        nombre = String.valueOf(authentication.getPrincipal());
        clave = String.valueOf(authentication.getCredentials());
        authorities = new ArrayList<GrantedAuthority>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        if(!ldapServices.loginUser(nombre, clave)){
            return null;
        }

        detail = new Admin(nombre, "admin@email.com", clave);

        return new SimpleAuthentication(nombre,authorities, detail, clave);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


}
