package com.DTO;

public class UserDTO {

    private String name = null;
    private String username = null;
    private String roleID = null;
    private String password = null;
    private String email = null;
    private String phoneNumber = null;

    public UserDTO() {
        this.name = "";
        this.username = "";
        this.roleID = "";
        this.password = "";
        this.email = "";
        this.phoneNumber = "";
    }

    public UserDTO(String Username, String Name, String password, String email, String phoneNumber, String roleID) {
        this.name = Name;
        this.username = Username;
        this.roleID = roleID;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
