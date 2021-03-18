package com.react.pnld.model;

public class Admin {
    private Long idAdmin;
    private String nombre;
    private String email;
    private String clave;
    private String role;

    public Admin(){}

    public Admin(String nombre, String email, String clave){
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.role = "ROLE_ADMIN";
    }

    public void setId(Long idAdmin){
        this.idAdmin = idAdmin;
    }

    public Long getId() {
        return idAdmin;
    }

    public void setUsername(String username){
        this.nombre = nombre;
    }

    public String getUsername(){
        return nombre;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){ return email;}

    public void setPassword(String clave){
        this.clave = clave;
    }

    public String getPassword(){
        return clave;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){return role;}



}
