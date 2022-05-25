package com.user;

import javax.management.relation.Role;

public class UserDTO {
    private String Username;
    private String Name;
    private String Password;
    private String Email;
    private String PhoneNumber;
    private String RoleID;

    public UserDTO(){
        this.Username = "";
        this.Name = "";
        this.Password = "";
        this.Email = "";
        this.PhoneNumber = "";
        this.RoleID = "";
    }

    public UserDTO(String Username, String Name, String Password, String Email, String PhoneNumber, String RoleID){
        this.Username = Username;
        this.Name = Name;
        this.Password = Password;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.RoleID = RoleID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String roleID) {
        RoleID = roleID;
    }


}
