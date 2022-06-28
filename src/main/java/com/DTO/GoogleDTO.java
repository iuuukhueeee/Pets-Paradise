package com.DTO;

public class GoogleDTO {
    private String id;
    private String email;
    private boolean verify_email;
    private String name;
    private String given_name;
    private String family_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerify_email() {
        return verify_email;
    }

    public void setVerify_email(boolean verify_email) {
        this.verify_email = verify_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    @Override
    public String toString() {
        return "GoogleDTO [id=" + id + ", email=" + email
                + ", verified_email=" + verify_email + ", name=" + name
                + ", given_name=" + given_name + ", family_name=" + family_name
                + "]";
    }
}
