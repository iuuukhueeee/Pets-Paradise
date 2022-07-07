package com.DTO;

public class BlogDTO {
    private String blogID;
    private String author;
    private String writtenDate;
    private String blogTitle;
    private String blogContent;
    private String blogDescription;

    public BlogDTO() {
        this.blogID = "";
        this.author = "";
        this.writtenDate = "";
        this.blogTitle = "";
        this.blogContent = "";
        this.blogDescription = "";
    }

    public BlogDTO(String blogID, String author, String writtenDate, String blogTitle, String blogContent, String blogDescription) {
        this.blogID = blogID;
        this.author = author;
        this.writtenDate = writtenDate;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.blogDescription = blogDescription;
    }

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(String writtenDate) {
        this.writtenDate = writtenDate;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }
}
