package com.react.pnld.authentication;

import com.react.pnld.model.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String nombre;

    private String email;

    private String clave;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String nombre, String email, String clave,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Admin admin) {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(admin.getRole()));

        return new UserDetailsImpl(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
