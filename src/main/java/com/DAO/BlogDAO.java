package com.DAO;

import com.DTO.BlogDTO;
import com.DTO.UserDTO;
import com.utils.DButils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlogDAO {

    private static final String LOAD_BLOG_BY_ID = "SELECT BlogID, Author, WrittenDate, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE BlogID=? AND Status=1";
    private static final String LOAD_BLOG_TEMPLATE = "SELECT BlogID, Author, WrittenDate, BlogTitle, BlogDescription FROM Blog WHERE Status=1 ORDER BY RAND() LIMIT 3";
    private static final String UPDATE_BLOG = "UPDATE Blog SET Author = ?, WrittenDate = ?, BlogTitle = ?, BlogContent = ?, BlogDescription = ? WHERE BlogID = ?";
    private static final String LOAD_ALL = "SELECT BlogID, Author, WrittenDate, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE Status=1";
    private static final String COUNT = "SELECT COUNT(*) AS NUM FROM Blog";
    private static final String CREATE_BLOG = "INSERT INTO Blog(BlogID, Author, WrittenDate, BlogTitle, BlogContent, BlogDescription, Status) VALUES(?,?,?,?,?,?,1)";
    private static final String DELETE_BLOG = "UPDATE Blog SET Status=0 WHERE BlogID=?";
    private static final String SEARCH_BLOG = "SELECT BlogID, Author, WrittenDate, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE BlogTitle LIKE ? AND Status=1";

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
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogContent = rs.getString("BlogContent");
                    String blogDescription = rs.getString("BlogDescription");
                    blog = new BlogDTO(blogID, author, writtenDate, blogTitle, blogContent, blogDescription);
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
                ptm = conn.prepareStatement(LOAD_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String blogID = rs.getString("BlogID");
                    String author = rs.getString("Author");
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogContent = rs.getString("BlogContent");
                    String blogDescription = rs.getString("BlogDescription");
                    listBlog.add(new BlogDTO(blogID, author, writtenDate, blogTitle, blogContent, blogDescription));
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
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogDescription = rs.getString("BlogDescription");
                    listBlogTemplate.add(new BlogDTO(blogID, author, writtenDate, blogTitle, "", blogDescription));
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

    public boolean updateBlog(UserDTO user, String ID, String title, String content, String description) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BLOG);
                ptm.setString(1, user.getName());
                ptm.setDate(2, new Date(System.currentTimeMillis()));
                ptm.setString(3, title);
                ptm.setString(4, content);
                ptm.setString(5, description);
                ptm.setString(6, ID);
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
                ptm = conn.prepareStatement(LOAD_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String blogID = rs.getString("BlogID");
                    String author = rs.getString("Author");
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogContent = rs.getString("BlogContent");
                    String blogDescription = rs.getString("BlogDescription");
                    listBlog.add(new BlogDTO(blogID, author, writtenDate, blogTitle, blogContent, blogDescription));
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

    public boolean createBlog(UserDTO user, String title, String content, String description) throws SQLException {
        boolean check = false;
        String id = "BLOG-";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int num = Integer.parseInt(rs.getString("NUM")) + 1;
                    String format = String.format("%03d", num);
                    id += format;
                }
                ptm = conn.prepareStatement(CREATE_BLOG);
                ptm.setString(1, id);
                ptm.setString(2, user.getName());
                ptm.setDate(3, new Date(System.currentTimeMillis()));
                ptm.setString(4, title);
                ptm.setString(5, content);
                ptm.setString(6, description);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean deleteBlog(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_BLOG);
                ptm.setString(1, ID);
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

    public List<BlogDTO> search (String search) throws SQLException {
        List<BlogDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BLOG);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String blogID = rs.getString("BlogID");
                    String author = rs.getString("Author");
                    String writtenDate = rs.getString("WrittenDate");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogContent = rs.getString("BlogContent");
                    String blogDescription = rs.getString("BlogDescription");
                    list.add(new BlogDTO(blogID, author, writtenDate, blogTitle, blogContent, blogDescription));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
}
