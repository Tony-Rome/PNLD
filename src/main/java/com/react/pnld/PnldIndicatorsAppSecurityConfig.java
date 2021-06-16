package com.react.pnld;

import com.react.pnld.authentication.LDAPAuthProvider;
import com.react.pnld.authentication.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class PnldIndicatorsAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public LDAPAuthProvider ldapAuthProvider() {
        LDAPAuthProvider ldapAuthProvider = new LDAPAuthProvider();
        return ldapAuthProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .usernameParameter("txtUsuario")
                .passwordParameter("txtClave")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /**
         *
         * username: admin
         * password: admin
         *
         * */

        auth.authenticationProvider(ldapAuthProvider());

       /*auth.userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder());*/


    }
}
