package com.Error;

public class AdminBlogError {
    private String titleError;
    private String contentError;
    private String descriptionError;

    public AdminBlogError() {
        this.titleError = "";
        this.contentError = "";
        this.descriptionError = "";
    }

    public AdminBlogError(String titleError, String contentError, String descriptionError) {
        this.titleError = titleError;
        this.contentError = contentError;
        this.descriptionError = descriptionError;
    }

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getContentError() {
        return contentError;
    }

    public void setContentError(String contentError) {
        this.contentError = contentError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }
}
