package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.DTO.ServiceDTO;
import com.utils.DButils;

public class ServiceDAO {

    private static final String GET_SIZE_SERVICE = "SELECT COUNT(ServiceID) AS SIZE FROM Service WHERE Status=1";
    private static final String CHECK_DUPLICATE = "SELECT ServiceName FROM Service WHERE ServiceID=?";
    private static final String ADD = "INSERT INTO Service(ServiceID, ServiceName, ServicePrice, ServiceDescription, Status) VALUES (?, ?, ?, ?, 1)";
    private static final String SEARCH_SERVICE = "SELECT ServiceID, ServiceName, ServicePrice, ServiceDescription, Status FROM Service WHERE ServiceName LIKE ? AND status=1";
    private static final String GET_ALL = "SELECT ServiceID, ServiceName, ServicePrice, ServiceDescription FROM Service WHERE status=1";
    private static final String UPDATE = "UPDATE Service SET  ServiceName=?, ServicePrice=?, ServiceDescription=? WHERE ServiceID=?";
    private static final String GET_BY_ID = "SELECT ServiceID, ServiceName, ServicePrice, ServiceDescription FROM Service WHERE ServiceID=? AND Status=1";
    private static final String DELETE = "UPDATE Service SET status=0 WHERE ServiceID=?";
    private static final String GET_SERVICE_PER_PAGE = "SELECT ServiceID, ServiceName, ServicePrice, ServiceDescription FROM Service WHERE Status=1 LIMIT ?, 3";


    public ServiceDTO getByID(String ID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ServiceDTO service = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BY_ID);
                ptm.setString(1, ID);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    String serviceID = rs.getString("ServiceID");
                    String serviceName = rs.getString("ServiceName");
                    float servicePrice = rs.getFloat("ServicePrice");
                    String serviceDescription = rs.getString("ServiceDescription");
                    service = new ServiceDTO(serviceID, serviceName , servicePrice, serviceDescription);
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

        return service;
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

    public boolean update(ServiceDTO service) throws SQLException {
        boolean check = false;

        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(UPDATE);
                pst.setString(1, service.getServiceName());
                pst.setFloat(2, service.getServicePrice());
                pst.setString(3, service.getServiceDescription());
                pst.setString(4, service.getServiceID());
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

    public boolean create(ServiceDTO service) throws SQLException {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean success = false;

        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(ADD);
                pst.setString(1, service.getServiceID());
                pst.setString(2, service.getServiceName());
                pst.setFloat(3, service.getServicePrice());
                pst.setString(4, service.getServiceDescription());

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

    public boolean checkDuplicate(String serviceID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, serviceID);
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

    public List<ServiceDTO> searchService(String keyword) throws SQLException {
        List<ServiceDTO> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DButils.getConnection();
            if (connection != null) {
                pst = connection.prepareStatement(SEARCH_SERVICE);
                pst.setString(1, "%" + keyword + "%");
                rs = pst.executeQuery();

                while (rs.next()) {
                    String ServiceID = rs.getString("ServiceID");
                    String ServiceName = rs.getString("ServiceName");
                    float ServicePrice = rs.getFloat("ServicePrice");
                    String ServiceDescription = rs.getString("ServiceDescription");
                    ServiceDTO service = new ServiceDTO(ServiceID, ServiceName, ServicePrice, ServiceDescription);
                    list.add(service);
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

    public String createID() throws SQLException {
        String service = "SERVICE-";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SIZE_SERVICE);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int size = Integer.parseInt(rs.getString("SIZE")) + 1;
                    service += String.format("%03d", size);
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
        return service;
    }

    public List<ServiceDTO> getAll() throws SQLException {
        List<ServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("ServiceID");
                    String name = rs.getString("ServiceName");
                    float price = rs.getFloat("ServicePrice");
                    String description = rs.getString("ServiceDescription");
                    list.add(new ServiceDTO(id, name, price, description));
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

    public List<ServiceDTO> getServicePerPage(String page) throws SQLException {
        List<ServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        Integer pageNumber = (Integer.parseInt(page) - 1) * 3;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SERVICE_PER_PAGE);
                ptm.setInt(1, pageNumber);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("ServiceID");
                    String name = rs.getString("ServiceName");
                    float price = rs.getFloat("ServicePrice");
                    String description = rs.getString("ServiceDescription");
                    list.add(new ServiceDTO(id, name, price, description));
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

    public int getSize() throws SQLException {
        int size = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SIZE_SERVICE);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    size = Integer.parseInt(rs.getString("SIZE"));
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
        return size;
    }

}
