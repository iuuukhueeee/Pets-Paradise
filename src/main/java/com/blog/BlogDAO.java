package com.blog;

import com.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {

    private static final String LOAD_BLOG = "SELECT BlogID, BlogTitle, BlogContent, BlogDescription FROM Blog WHERE Status=1";

    public List<BlogDTO> loadBlog() throws SQLException {
        List<BlogDTO> listBlog = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOAD_BLOG);
                rs = ptm.executeQuery();
                while (!rs.next()) {
                    String blogID = rs.getString("BlogID");
                    String blogTitle = rs.getString("BlogTitle");
                    String blogContent = rs.getString("BlogContent");
                    String blogDescription = rs.getString("BlogDescription");
                    listBlog.add(new BlogDTO(blogID, blogTitle, blogContent, blogDescription));
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
