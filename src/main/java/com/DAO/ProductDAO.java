package com.DAO;

import com.DTO.CartDTO;
import com.DTO.ProductDTO;
import com.utils.DButils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utils.*;

public class ProductDAO {
    private static final String UPDATE_QUANTITY_ON_HAND = "UPDATE Product SET Quantity= Quantity - ? where ProductID=?";
    private static final String DELETE = "UPDATE Product SET Status=0 WHERE ProductID=?";
    private static final String CHECK_DUPLICATE = "SELECT Name FROM Product WHERE ProductID=?";
    private static final String ADD = "INSERT INTO Product(ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
    private static final String SEARCH_PRODUCT = "SELECT ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate FROM Product WHERE Name LIKE ? AND Status=1";
    private static final String GET_ALL = "SELECT ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate FROM Product WHERE Status=1";
    private static final String UPDATE = "UPDATE Product SET ProductCategoryID=?, Name=?, Quantity=?, Image=?, Price=?, ImportDate=?, ExpiredDate=? WHERE ProductID=?";
    private static final String GET_BY_ID = "SELECT ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate FROM Product WHERE ProductID=?";

    public boolean updateQuantity(String productID, CartDTO cart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY_ON_HAND);
                ptm.setInt(1, cart.getQuantity());
                ptm.setString(2, productID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                ptm.close();
            }
        }
        return check;
    }

    public boolean delete(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, ID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean updateProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getCategoryID());
                ptm.setString(2, product.getName());
                ptm.setInt(3, product.getQuantity());
                ptm.setString(4, product.getImage());
                ptm.setFloat(5, product.getPrice());
                ptm.setDate(6, product.getImportDate());
                ptm.setDate(7, product.getExpiredDate());
                ptm.setString(8, product.getProductID());
                check = ptm.executeUpdate() > 0;
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

    public ProductDTO getByID(String ID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ProductDTO product = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BY_ID);
                ptm.setString(1, ID);
                rs = ptm.executeQuery();

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
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return product;
    }
    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PRODUCT);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();

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
                    productList.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return productList;
    }

    public List<ProductDTO> getAll() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();

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
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    public boolean create(ProductDTO product) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean success = false;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getCategoryID());
                ptm.setString(3, product.getName());
                ptm.setInt(4, product.getQuantity());
                ptm.setString(5, product.getImage());
                ptm.setFloat(6, product.getPrice());
                ptm.setDate(7, product.getImportDate());
                ptm.setDate(8, product.getExpiredDate());
                success = ptm.executeUpdate() > 0;
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
