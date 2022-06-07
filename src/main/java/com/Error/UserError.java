/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Error;

/**
 *
 * @author PC
 */
public class UserError {
    private String userNameError;
    private String nameError;
    private String passwordError;
    private String emailError;
    private String phoneNumberError;
    private String roleIDError;


    public UserError() {
        this.userNameError = "";
        this.nameError = "";
        this.passwordError = "";
        this.emailError = "";
        this.phoneNumberError = "";
        this.roleIDError = "";
    }


    public UserError(String userNameError, String nameError, String passwordError, String emailError, String phoneNumberError, String roleIDError) {
        this.userNameError = userNameError;
        this.nameError = nameError;
        this.passwordError = passwordError;
        this.emailError = emailError;
        this.phoneNumberError = phoneNumberError;
        this.roleIDError = roleIDError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }
}
