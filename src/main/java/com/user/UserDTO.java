/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

/**
 *
 * @author Nguyen Dang Khoa
 */

public class UserDTO {


    private String Name = null;
    private String Username = null;
    private String RoleID = null;
    private String Password = null;

    private String Email = null;

    private String PhoneNumber = null;

    public UserDTO() {
        this.Name = "";
        this.Username = "";
        this.RoleID = "";
        this.Password = "";
        this.Email = "";
        this.PhoneNumber = "";
    }

    public UserDTO(String Username, String Name, String password ,String email, String phoneNumber, String roleID) {
        this.Name = Name;
        this.Username = Username;
        this.RoleID = roleID;
        this.Password = password;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setRoleID(String roleID) {
        this.RoleID = roleID;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getUsername() {
        return Username;
    }

    public String getRoleID() {
        return RoleID;
    }
    public String getPassword() {
        return Password;
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
