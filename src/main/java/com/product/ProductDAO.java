package com.product;

import com.checkout.Item;
import com.utils.DButils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    private static final String ADD = "INSERT INTO Product(ProductID, Name, Quantity, Image, Price, ImportDate, ExpiredDate, Status) VALUES(?,?,?,?,?,?,?,1)";
    private static final String UPDATE = "UPDATE Product SET Name=?, Quantity=?, Image=?, Price=?, ImportDate=?, ExpiredDate=? where ProductID=?";
    private static final String UPDATE_QUANTITY = "UPDATE Product SET Quantity= Quantity - ? where ProductID=?";

    private static final String SEARCH = "SELECT ProductID, Quantity, Image, Price, ImportDate, ExpiredDate from Product WHERE Name like ? AND Status=1";
    private static final String DELETE = "UPDATE Product SET Status=0 WHERE ProductID=?";

    private static final String GET_ALL = "SELECT ProductID, Name, Quantity, Image, Price, ImportDate, ExpiredDate FROM Product";

    private static final String GET_BY_ID = "SELECT ProductID, ProductCategoryID, Name, Quantity, Image, Price, ImportDate, ExpiredDate FROM Product WHERE productID=?";

    public boolean updateQuantity(Item item) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, item.getProduct().getQuantity());
                ptm.setString(2, item.getProduct().getProductID());
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

    public List<ProductDTO> getAll() throws SQLException{
        List<ProductDTO> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(GET_ALL);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String name = rs.getString("Name");
                    int quantity = Integer.parseInt(rs.getString("Quantity"));
                    String image = rs.getString("Image");
                    float price = Float.parseFloat(rs.getString("Price"));
                    Date importDate = rs.getDate("ImportDate");
                    Date expiredDate = rs.getDate("ExpiredDate");
                    ProductDTO product = new ProductDTO(productID, name, quantity, image, price, importDate,
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

    public ProductDTO getByID(String ID) throws SQLException {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ProductDTO product = null;

        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(GET_BY_ID);
                pst.setString(1, ID);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String Name = rs.getString("Name");
                    int quantity = Integer.parseInt(rs.getString("Quantity"));
                    String image = rs.getString("Image");
                    float price = Float.parseFloat(rs.getString("Price"));
                    Date importDate = rs.getDate("ImportDate");
                    Date expiredDate = rs.getDate("ExpiredDate");
                    product = new ProductDTO(productID, Name, quantity, image, price, importDate,
                            expiredDate);
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

    public boolean deleteProduct(String ProductID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, ProductID);
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
        List<ProductDTO> productList = new ArrayList<>();
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
                    String productID = rs.getString("ProductID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String image = rs.getString("Image");
                    float price = rs.getFloat("Price");
                    Date importDate = rs.getDate("ImportDate");
                    Date expiredDate = rs.getDate("ExpiredDate");
                    productList.add(new ProductDTO(productID, name, quantity, image, price, importDate, expiredDate));
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
