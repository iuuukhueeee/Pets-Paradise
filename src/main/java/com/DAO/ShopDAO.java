package com.DAO;

import com.DTO.ShopDTO;
import com.DTO.ShopServiceDetailDTO;
import com.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopDAO {

    private static final String GET_ALL_SHOP = "SELECT ShopID, ShopName, ShopLocation FROM Shop WHERE Status=1";
    private static final String GET_SHOP_INFOMATION = "SELECT ShopID, ShopName, ShopLocation FROM Shop WHERE ShopID=? AND Status=1";

    public List<ShopDTO> getAll() throws SQLException {
        List<ShopDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_SHOP);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("ShopID");
                    String name = rs.getString("ShopName");
                    String shopLocation = rs.getString("ShopLocation");
                    list.add(new ShopDTO(id, name, shopLocation));
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

    public Map<String, ShopDTO> getShopInfo(List<ShopServiceDetailDTO> shopService) throws SQLException {
        Map<String, ShopDTO> shopMap = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SHOP_INFOMATION);
                for (ShopServiceDetailDTO item: shopService) {
                    ptm.setString(1, item.getShopID());
                    rs = ptm.executeQuery();
                    if (rs.next()) {
                        String id = rs.getString("ShopID");
                        String name = rs.getString("ShopName");
                        String location = rs.getString("ShopLocation");
                        ShopDTO shop = new ShopDTO(id, name, location);
                        if (!shopMap.containsKey(id)) {
                            shopMap.put(id, shop);
                        }
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
        return shopMap;
    }
}
