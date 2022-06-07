package com.user;

public class UserDTO {

    private String Username = null;
    private String Name = null;
    private String RoleID = null;
    private String Password = null;
    private String Email = null;
    private String PhoneNumber = null;

    public UserDTO() {
        this.Username = "";
        this.Name = "";
        this.RoleID = "";
        this.Password = "";
        this.Email = "";
        this.PhoneNumber = "";
    }

    public UserDTO(String Username, String Name , String password, String email, String phoneNumber, String roleID) {
        this.Username = Username;
        this.Name = Name;
        this.RoleID = roleID;
        this.Password = password;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String roleID) {
        this.RoleID = roleID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }
}
