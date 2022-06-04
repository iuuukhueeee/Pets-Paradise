package com.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.product.*;
import com.utils.*;
import com.shopping.*;

public class ProductDAO {

    private static final String UPDATE_QUANTITY = "UPDATE Product SET Quantity=? WHERE ProductID=?";

    private static final String CHECK_DUPLICATE = "SELECT Name FROM Product WHERE ProductID=?";
    private static final String ADD = "INSERT INTO Product(ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
    private static final String SEARCH_PRODUCT = "SELECT ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate FROM Product WHERE Name LIKE ? AND Status=1";
    private static final String GET_ALL = "SELECT ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate FROM Product WHERE Status=1";
    private static final String UPDATE = "UPDATE Product SET ProductCategoryID=?, Name=?, Quantity=?, Image=?, Price=?, ImportDate=?, ExpiredDate=? WHERE ProductID=?";
    private static final String GET_ID = "SELECT ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate FROM Product WHERE ProductID=?";
    private static final String DELETE = "UPDATE Product SET Status=0 WHERE ProductID=?";

    public boolean updateQuantity(String id, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, quantity);
                ptm.setString(2, id);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean delete(String ID) throws SQLException {
        boolean check = false;

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(DELETE);
                pst.setString(1, ID);
                check = pst.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return check;
    }

    public boolean update(ProductDTO product) throws SQLException {
        boolean check = false;

        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(UPDATE);
                pst.setString(1, product.getCategoryID());
                pst.setString(2, product.getName());
                pst.setInt(3, product.getQuantity());
                pst.setString(4, product.getImage());
                pst.setFloat(5, product.getPrice());
                pst.setDate(6, product.getImportDate());
                pst.setDate(7, product.getExpiredDate());
                pst.setString(8, product.getProductID());
                check = pst.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return check;
    }

    public ProductDTO getByID(String ID) throws SQLException {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ProductDTO product = null;

        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(GET_ID);
                pst.setString(1, ID);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productCategoryID = rs.getString("ProductCategoryID");
                    String productName = rs.getString("Name");
                    int quantity = Integer.parseInt(rs.getString("Quantity"));
                    String image = rs.getString("Image");
                    float price = Float.parseFloat(rs.getString("Price"));
                    Date importDate = ValidUtils.isValidDate(rs.getString("ImportDate"));
                    Date expiredDate = ValidUtils.isValidDate(rs.getString("ExpiredDate"));
                    product = new ProductDTO(productID, productCategoryID, productName, quantity, image, price, importDate, expiredDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return product;
    }

    public List<ProductDTO> searchproduct(String keyword) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(SEARCH_PRODUCT);
                pst.setString(1, "%" + keyword + "%");
                rs = pst.executeQuery();

                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productCategoryID = rs.getString("ProductCategoryID");
                    String productName = rs.getString("Name");
                    int quantity = Integer.parseInt(rs.getString("Quantity"));
                    String image = rs.getString("Image");
                    float price = Float.parseFloat(rs.getString("Price"));
                    Date importDate = ValidUtils.isValidDate(rs.getString("ImportDate"));
                    Date expiredDate = ValidUtils.isValidDate(rs.getString("ExpiredDate"));
                    ProductDTO product = new ProductDTO(productID, productCategoryID, productName, quantity, image, price, importDate,
                            expiredDate);
                    list.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return list;
    }

    public List<ProductDTO> getAll() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(GET_ALL);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productCategoryID = rs.getString("ProductCategoryID");
                    String productName = rs.getString("Name");
                    int quantity = Integer.parseInt(rs.getString("Quantity"));
                    String image = rs.getString("Image");
                    float price = Float.parseFloat(rs.getString("Price"));
                    Date importDate = ValidUtils.isValidDate(rs.getString("ImportDate"));
                    Date expiredDate = ValidUtils.isValidDate(rs.getString("ExpiredDate"));
                    ProductDTO product = new ProductDTO(productID, productCategoryID, productName, quantity, image, price, importDate,
                            expiredDate);
                    list.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return list;
    }

    public boolean create(ProductDTO product) throws SQLException {
        Connection connection = null;
        PreparedStatement pst = null;

        boolean success = false;

        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(ADD);
                pst.setString(1, product.getProductID());
                pst.setString(2, product.getCategoryID());
                pst.setString(3, product.getName());
                pst.setInt(4, product.getQuantity());
                pst.setString(5, product.getImage());
                pst.setFloat(6, product.getPrice());
                pst.setDate(7, product.getImportDate());
                pst.setDate(8, product.getExpiredDate());
                success = pst.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }

    public boolean checkDuplicate(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }
}
