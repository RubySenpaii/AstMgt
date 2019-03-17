/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.SupplierItem;

/**
 *
 * @author Bryll Joey Delfin
 */
public class SupplierItemService {

    private String AddSupplierItemQuery = "INSERT INTO SupplierItem(" + SupplierItem.COLUMN_SUPPLIER_ITEM_ID + "," + SupplierItem.COLUMN_SUPPLIER_ITEM_ASSET_ID + ","
            + SupplierItem.COLUMN_SUPPLIER_ITEM_PRICE + ")VALUES(?,?,?)";

    private String FindSupplierItemByIdQuery = "SELECT * FROM SupplierItem WHERE " + SupplierItem.COLUMN_SUPPLIER_ITEM_ID + " = ?";
    private String FindAllSupplierQuery = "SELECT * FROM SupplierItem ;";

    public int AddNewSupplier(SupplierItem s) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(AddSupplierItemQuery);
            ps.setInt(1, s.SupplierId);
            ps.setInt(2, s.AssetId);
            ps.setDouble(3, s.price);
            int res = ps.executeUpdate();
            ps.close();
            conn.close();
            return res;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    public ArrayList<SupplierItem> FindSupplierItemById(int suppno) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindSupplierItemByIdQuery);
            ps.setInt(1, suppno);

            ArrayList<SupplierItem> elist = getResult(ps.executeQuery());
            ps.close();
            conn.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<SupplierItem> FindAllSupplier() {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindAllSupplierQuery);
            ArrayList<SupplierItem> elist = getResult(ps.executeQuery());

            ps.close();
            conn.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    private ArrayList<SupplierItem> getResult(ResultSet rs) throws SQLException {
        ArrayList<SupplierItem> suppliers = new ArrayList<SupplierItem>();
        while (rs.next()) {
            SupplierItem e = new SupplierItem();
            e.SupplierId = rs.getInt(SupplierItem.COLUMN_SUPPLIER_ITEM_ID);
            e.AssetId = rs.getInt(SupplierItem.COLUMN_SUPPLIER_ITEM_ASSET_ID);
            e.price = rs.getDouble(SupplierItem.COLUMN_SUPPLIER_ITEM_PRICE);
            suppliers.add(e);
        }
        rs.close();
        return suppliers;
    }
}
