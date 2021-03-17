package com.react.pnld.authentication;

public class Admin {
    private Long id;
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

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){ return email;}

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){return role;}



}
