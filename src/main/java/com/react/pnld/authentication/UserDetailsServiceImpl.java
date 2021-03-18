package com.react.pnld.authentication;

import com.react.pnld.mappers.AdminMapper;
import com.react.pnld.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        Admin admin = adminMapper.findByUsername(nombre);

        if(admin == null)
            throw new UsernameNotFoundException("Credenciales erróneas");

        return UserDetailsImpl.build(admin);
    }
}
