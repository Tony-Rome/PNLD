package com.react.pnld.model;

import java.io.Serializable;

public class Admin implements Serializable {
    private Long idAdmin;
    private String username;
    private String email;
    private String password;
    private String role;

    public Admin(){}

    public Admin(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = "ROLE_ADMIN";
    }

    public void setId(Long idAdmin){
        this.idAdmin = idAdmin;
    }

    public Long getId() {
        return idAdmin;
    }

    public void setUsername(String username){this.username = username;}

    public String getUsername(){return username;}

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){ return email;}

    public void setPassword(String password){this.password = password;}

    public String getPassword(){return password;}

    public void setRole(String role){ this.role = role;}

    public String getRole(){return role;}

    @Override
    public String toString(){
        return "Id: "+getId().toString()+" Nombre: "+getUsername()+" Email: "+getEmail();
    }

}
