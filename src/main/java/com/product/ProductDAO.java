package com.product;

import com.utils.DButils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    private static final String ADD = "INSERT INTO Product(ProductID, Name, Quantity, Image, Price, ImportDate, ExpiredDate, Status) VALUES(?,?,?,?,?,?,?,1)";
    private static final String UPDATE = "UPDATE Product SET Name=?, Quantity=?, Image=?, Price=?, ImportDate=?, ExpiredDate=? where ProductID=?";
    private static final String SEARCH = "SELECT ProductID, Quantity, Image, Price, ImportDate, ExpiredDate from Product WHERE Name like ? AND Status=1";
    private static final String DELETE = "UPDATE Product SET Status=0 WHERE ProductID=?";

    public boolean deleteProduct(int ProductID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setInt(1, ProductID);
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

    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> ProductList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String ProductID = rs.getString("ProductID");
                    String Name = rs.getString("Name");
                    int Quantity = rs.getInt("Quantity");
                    String Image = rs.getString("Image");
                    float Price = rs.getFloat("Price");
                    Date ImportDate = rs.getDate("ImportDate");
                    Date ExpiredDate = rs.getDate("ExpiredDay");
                    ProductList.add(new ProductDTO(ProductID, Name, Quantity, Image, Price, ImportDate, ExpiredDate));
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
        return ProductList;
    }


    public boolean updateProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getName());
                ptm.setInt(3, product.getQuantity());
                ptm.setString(4, product.getImage());
                ptm.setFloat(5, product.getPrice());
                ptm.setDate(6, (Date) product.getImportDate());
                ptm.setDate(7, (Date) product.getImportDate());
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

    public boolean addProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getName());
                ptm.setInt(3, product.getQuantity());
                ptm.setString(4, product.getImage());
                ptm.setFloat(5, product.getPrice());
                ptm.setDate(6, (Date) product.getImportDate());
                ptm.setDate(7, (Date) product.getImportDate());
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
}
