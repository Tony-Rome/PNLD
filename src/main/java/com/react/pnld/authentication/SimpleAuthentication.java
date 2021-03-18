package com.react.pnld.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class SimpleAuthentication implements Authentication {

    private String principal;
    private List<GrantedAuthority> authorities;
    private Object detail;
    private boolean authenticated;
    private String credential;

    public SimpleAuthentication(String principal, List<GrantedAuthority> authorities, Object detail, String clave){
        this.principal = principal;
        this.authorities = authorities;
        this.detail = detail;
        this.authenticated = true;
        this.credential = clave;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return credential;
    }

    @Override
    public Object getDetails() {
        return detail;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return principal;
    }
}
