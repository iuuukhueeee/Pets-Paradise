package com.DAO;

import com.DTO.ShopServiceDetailDTO;
import com.utils.DButils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopServiceDetailDAO {
    private static final String GET_DETAIL_SHOP = "SELECT ShopServiceDetailID, ShopID, ServiceID, AdjustPrice FROM ShopServiceDetail WHERE ServiceID LIKE ? AND Status=1";

    public List<ShopServiceDetailDTO> getSupportedShop(String id) throws SQLException {
        List<ShopServiceDetailDTO> listServiceDetail = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_DETAIL_SHOP);
                ptm.setString(1, "%" + id + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String detailID = rs.getString("ShopServiceDetailID");
                    String shopID = rs.getString("ShopID");
                    String serviceID = rs.getString("ServiceID");
                    float adjustPrice = Float.parseFloat(rs.getString("AdjustPrice"));
                    listServiceDetail.add(new ShopServiceDetailDTO(detailID, shopID, serviceID, adjustPrice));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return listServiceDetail;
    }
}
