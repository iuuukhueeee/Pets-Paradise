package com.DAO;

import com.DTO.BlogDTO;
import com.DTO.UserDTO;
import com.utils.DButils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlogDAO {

    private static final String LOAD_BLOG = "SELECT BlogID, Author, AuthorAvatar, WrittenDate, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE Status=1";
    private static final String LOAD_BLOG_BY_ID = "SELECT BlogID, Author, AuthorAvatar, WrittenDate, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE BlogID LIKE ? AND Status=1";
    private static final String LOAD_BLOG_TEMPLATE = "SELECT BlogID, Author, AuthorAvatar, WrittenDate, BlogTitle, BlogDescription FROM Blog WHERE Status=1";
    private static final String UPLOAD_BLOG = "INSERT INTO Blog(BlogID, Author, AuthorAvatar, WrittenDate, BlogTitle, BlogContent, BlogDescription, Status) VALUES(?, ?, ?, ?, ?, ?, ?, 1)";
    private static final String LOAD_ALL = "SELECT BlogID, Author, AuthorAvatar, WrittenDate, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE Status=1";

    public BlogDTO loadByID(String id) throws SQLException {
        BlogDTO blog = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOAD_BLOG_BY_ID);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String blogID = rs.getString("BlogID");
                    String author = rs.getString("Author");
                    String authorAvatar = rs.getString("AuthorAvatar");
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogContent = rs.getString("BlogContent");
                    String blogDescription = rs.getString("BlogDescription");
                    blog = new BlogDTO(blogID, author, authorAvatar, writtenDate, blogTitle, blogContent, blogDescription);
                }
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return blog;
    }


    public List<BlogDTO> loadListBlog() throws SQLException {
        List<BlogDTO> listBlog = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOAD_BLOG);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String blogID = rs.getString("BlogID");
                    String author = rs.getString("Author");
                    String authorAvatar = rs.getString("AuthorAvatar");
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogContent = rs.getString("BlogContent");
                    String blogDescription = rs.getString("BlogDescription");
                    listBlog.add(new BlogDTO(blogID, author, authorAvatar, writtenDate, blogTitle, blogContent, blogDescription));
                }
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return listBlog;
    }

    public List<BlogDTO> loadListBlogTemplate() throws SQLException {
        List<BlogDTO> listBlogTemplate = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOAD_BLOG_TEMPLATE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String blogID = rs.getString("BlogID");
                    String author = rs.getString("Author");
                    String authorAvatar = rs.getString("AuthorAvatar");
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogDescription = rs.getString("BlogDescription");
                    listBlogTemplate.add(new BlogDTO(blogID, author, authorAvatar, writtenDate, blogTitle, "", blogDescription));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return listBlogTemplate;
    }

    public boolean uploadBlog(UserDTO user,String title,  String content, String descrition) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPLOAD_BLOG);
                ptm.setString(1, "BLOG-" + UUID.randomUUID().toString().substring(0,15));
                ptm.setString(2, user.getName());
                ptm.setString(3, "");
                ptm.setDate(4, new Date(System.currentTimeMillis()));
                ptm.setString(5, title);
                ptm.setString(6, content);
                ptm.setString(7, descrition);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public List<BlogDTO> loadAll() throws SQLException {
        List<BlogDTO> listBlog = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOAD_BLOG);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String blogID = rs.getString("BlogID");
                    String author = rs.getString("Author");
                    String authorAvatar = rs.getString("AuthorAvatar");
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogContent = rs.getString("BlogContent");
                    String blogDescription = rs.getString("BlogDescription");
                    listBlog.add(new BlogDTO(blogID, author, authorAvatar, writtenDate, blogTitle, blogContent, blogDescription));
                }
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return listBlog;
    }
}
