package com.react.pnld.authentication;

import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.ResultCode;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.rmi.Naming;
import java.util.Hashtable;

public class LDAPServices {

    private String providerUrl = "ldap://localhost:8389/dc=springframework,dc=org";
    private String domain = "";
    private String group = "";
    private String admin = "";
    private String password = "";

    public String getProviderUrl(){return providerUrl;}
    public String getDomain(){return domain;}
    public String getGroup(){return group;}
    public String getAdmin(){return admin;}
    public String getPassword(){return password;}

    public void setProviderUrl(String providerUrl){this.providerUrl = providerUrl;}
    public void setDomain(String domain){this.domain = domain;}
    public void setGroup(String group){this.group = group;}
    public void setAdmin(String admin){this.admin = admin;}
    public void setPassword(String password){this.password = password;}

    public boolean loginUser(String username, String password) {
        InitialDirContext dir = null;
        Hashtable<String, String> env = new Hashtable<String, String>();
        boolean result = false;
        long errorCode  = 404;

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, providerUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, String.format("uid=%s,ou=people,dc=springframework,dc=org",username));
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            dir = new InitialDirContext(env);
            System.out.println("Connected");
            System.out.println(dir.getEnvironment());
            System.out.println(dir.list(username));
        }catch (AuthenticationException ex){
            System.out.println("Credenciales incorrectas");
        } catch (NamingException ex){
            System.out.println("Error al crear contexto");
        }finally {
            try{
                if(dir != null){
                    dir.close();
                    result = true;
                }
            }catch (NamingException ex){
                System.out.println("Error al cerrar conexión");
            }
        }

        return result;
    }
}
