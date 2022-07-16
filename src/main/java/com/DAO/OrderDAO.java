package com.DAO;

import com.DTO.IncomeDTO;
import com.DTO.OrderDTO;
import com.utils.DButils;
import com.utils.DateUtils;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class OrderDAO {
    private static final String CREATE = "INSERT INTO Orders(OrderID, OrderDate, Username, Status) VALUES(?,?,?,2)";
    private static final String UPDATE = "UPDATE Orders SET OrderDate=?, Username=?, FeedbackOrder=? WHERE OrderID=?";
    private static final String DELETE = "UPDATE Orders SET Status=0 WHERE OrderID=?";
    private static final String GET_ORDERS = "SELECT OrderID, OrderDate, Username, FeedbackOrder FROM Orders WHERE status = 1 ";
    private static final String SEARCH_ORDER = "SELECT OrderID, OrderDate, Username, FeedbackOrder FROM Orders WHERE OrderID LIKE ? AND Status = 1";
    private static final String GET_CART_BY_USERNAME = "SELECT OrderID, OrderDate FROM Orders WHERE Status=2 AND Username=?";
    private static final String UPDATE_TOTAL = "UPDATE Orders SET Total=? WHERE OrderID=?";
    private static final String CHECKOUT = "UPDATE Orders SET Status=1 WHERE OrderID=?";
    private static final String TOTAL_INCOME_A_MONTH = "select sum(total) as Total, monthname(OrderDate) as Month from Orders group by month(OrderDate)";
    private static final String GET_ORDERED_BY_USERNAME = "SELECT OrderID, OrderDate, FeedbackOrder FROM Orders WHERE Status=1 AND Username=?";


    public boolean deleteOrder(String OrderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, OrderID);
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

    public boolean updateOrder(OrderDTO order) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, order.getOrderID());
                ptm.setDate(2, (Date) order.getOrderDate());
                ptm.setString(3, order.getUsername());
                ptm.setString(4, order.getFeedBackOrder());
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


    public OrderDTO createOrder(String username) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        String orderID;
        OrderDTO order = null;
        try {
            Date orderDate = new Date(DateUtils.now());
            UUID uuid = UUID.randomUUID();
            conn = DButils.getConnection();
            if (conn != null) {
                orderID = "ORDER-" + uuid;
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, orderID);
                ptm.setDate(2, orderDate);
                ptm.setString(3, username);
                boolean check = ptm.executeUpdate() > 0;
                if (check) order = new OrderDTO(orderID, orderDate, username, "");
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
        return order;
    }

    public List<OrderDTO> getAll() throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDERS);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    String username = rs.getString("Username");
                    String feedbackOrder = rs.getString("FeedbackOrder");
                    OrderDTO order = new OrderDTO(orderID, orderDate, username, feedbackOrder);
                    list.add(order);
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

    public List<OrderDTO> getListOrder(String search) throws SQLException {
        List<OrderDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ORDER);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    String username = rs.getString("Username");
                    String feedbackOrder = rs.getString("FeedbackOrder");
                    OrderDTO order = new OrderDTO(orderID, orderDate, username, feedbackOrder);
                    orderList.add(order);
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
        return orderList;
    }


    public OrderDTO getByID(String ID) throws SQLException {
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDERS);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    String username = rs.getString("Username");
                    String feedbackOrder = rs.getString("FeedbackOrder");
                    order = new OrderDTO(orderID, orderDate, username, feedbackOrder);
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

        return order;
    }

    public OrderDTO getCartByUsername(String username) throws SQLException {
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CART_BY_USERNAME);
                ptm.setString(1, username);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    order = new OrderDTO(id, orderDate, username, "");
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
        return order;
    }


    public boolean updateTotal(String orderID, float total) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_TOTAL);
                ptm.setFloat(1, total);
                ptm.setString(2, orderID);
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

    public boolean checkout(String orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECKOUT);
                ptm.setString(1, orderID);
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

    public Map<String, IncomeDTO> getTotalIncomeAMonth() throws SQLException {
        Map<String, IncomeDTO> map = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TOTAL_INCOME_A_MONTH);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    float total = Float.parseFloat(rs.getString("Total"));
                    String month = rs.getString("Month");
                    if (!map.containsKey(month)) {
                        map.put(month, new IncomeDTO(total, month));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return map;
    }

    public List<OrderDTO> getOrderByUsername(String username) throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDERED_BY_USERNAME);
                ptm.setString(1, username);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("OrderID");
                    Date date = rs.getDate("OrderDate");
                    String feedback = rs.getString("FeedbackOrder");
                    list.add(new OrderDTO(id, date, username, feedback));
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
