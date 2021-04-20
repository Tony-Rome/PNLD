package com.react.pnld.authentication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Service
public class LDAPServices {

    private static final Logger logger = LoggerFactory.getLogger(LDAPServices.class);

    @Value("${provider-url}")
    private String providerUrl;

    @Value("${ldap-factory}")
    private String ldapFactory;

    private String ldapAuthentication = "simple";

    public boolean loginUser(String username, String password) {

        InitialDirContext dir = null;
        Hashtable<String, String> env = new Hashtable<String, String>();
        boolean result = false;

        env.put(Context.INITIAL_CONTEXT_FACTORY, ldapFactory);
        env.put(Context.PROVIDER_URL, providerUrl);
        env.put(Context.SECURITY_AUTHENTICATION, ldapAuthentication);
        env.put(Context.SECURITY_PRINCIPAL, String.format("uid=%s,ou=people,dc=springframework,dc=org", username));
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            dir = new InitialDirContext(env);
        } catch (AuthenticationException ex) {
            logger.info("AuthenticationException", ex);
        } catch (NamingException ex) {
            logger.info("NamingException", ex);
        } finally {
            try {
                if (dir != null) {
                    dir.close();
                    result = true;
                }
            } catch (NamingException ex) {
                logger.info("NamingException close connection", ex);
            }
        }

        return result;
    }
}
