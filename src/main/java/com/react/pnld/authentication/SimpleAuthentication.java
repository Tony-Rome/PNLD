package com.react.pnld.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class SimpleAuthentication implements Authentication {

    private static final long serialVersionUID = -7899424530535881138L;
    private String principal;
    private List<GrantedAuthority> authorities;
    private Object detail;
    private boolean authenticated;
    private String credential;

    public SimpleAuthentication(String p, List<GrantedAuthority> auths, Object d, String password){
        this.principal = p;
        this.authorities = auths;
        this.detail = d;
        this.authenticated = true;
        this.credential = password;
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
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        authenticated = b;
    }

    @Override
    public String getName() {
        return principal;
    }
}
