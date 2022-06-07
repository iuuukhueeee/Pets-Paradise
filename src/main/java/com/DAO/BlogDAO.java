package com.DAO;

import com.DTO.BlogDTO;
import com.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {

    private static final String LOAD_BLOG = "SELECT BlogID, Author, AuthorAvatar, WrittenDate, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE Status=1";
    private static final String LOAD_BLOG_BY_ID = "SELECT BlogID, Author, AuthorAvatar, WrittenDate, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE BlogID LIKE ? AND Status=1";
    private static final String LOAD_BLOG_TEMPLATE = "SELECT BlogID, Author, AuthorAvatar, WrittenDate, BlogTitle, BlogDescription FROM Blog WHERE Status=1";

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
}
