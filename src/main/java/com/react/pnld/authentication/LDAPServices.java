package com.react.pnld.authentication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LDAPServices {

    private static final Logger logger = LoggerFactory.getLogger(LDAPServices.class);

    private String providerUrl = "ldap://localhost:8389/dc=springframework,dc=org";

    public String getProviderUrl(){ return providerUrl; }

    public void setProviderUrl(String providerUrl){ this.providerUrl = providerUrl; }

    public boolean loginUser(String nombre, String clave) {

        InitialDirContext dir = null;
        Hashtable<String, String> env = new Hashtable<String, String>();
        boolean result = false;

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, providerUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, String.format("uid=%s,ou=people,dc=springframework,dc=org",nombre));
        env.put(Context.SECURITY_CREDENTIALS, clave);

        try {
            dir = new InitialDirContext(env);
        }catch (AuthenticationException ex){
            logger.info("AuthenticationException", ex);
        } catch (NamingException ex){
            logger.info("NamingException", ex);
        }finally {
            try{
                if(dir != null){
                    dir.close();
                    result = true;
                }
            }catch (NamingException ex){
                logger.info("NamingException close connection", ex);
            }
        }

        return result;
    }
}
