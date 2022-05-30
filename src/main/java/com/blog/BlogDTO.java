package com.blog;

public class BlogDTO {
    private String blogID;
    private String blogTitle;
    private String blogContent;
    private String blogDescription;

    public BlogDTO() {
        this.blogID = "";
        this.blogTitle = "";
        this.blogContent = "";
        this.blogDescription = "";
    }

    public BlogDTO(String blogID, String blogTitle, String blogContent, String blogDescription) {
        this.blogID = blogID;
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
