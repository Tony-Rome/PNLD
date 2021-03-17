package com.react.pnld;

import com.react.pnld.authentication.LDAPAuthProvider;
import com.react.pnld.authentication.UserDetailsImpl;
import com.react.pnld.authentication.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;

@EnableWebSecurity
public class PnldIndicatorsAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public LDAPAuthProvider ldapAuthProvider(){
        LDAPAuthProvider ldapAuthProvider = new LDAPAuthProvider();
        return ldapAuthProvider;
    }

   @Bean
   public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .usernameParameter("txtUsuario")
                    .passwordParameter("txtClave")
                .defaultSuccessUrl("/scheduleLoadFilePost",true)
                .and()
                .logout()
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(ldapAuthProvider());
        /*auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupRoleAttribute("cn")
                .rolePrefix("ROLE_")
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");*/

       /*auth.userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder());*/

        /*auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");*/
    }
}
