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
import java.sql.Statement;
import java.util.ArrayList;
import objects.SupplierItem;

/**
 *
 * @author Bryll Joey Delfin
 */
public class SupplierItemService {

    private String AddSupplierItemQuery = "INSERT INTO SupplierItem(" + SupplierItem.COLUMN_SUPPLIER_ITEM_ID + "," + SupplierItem.COLUMN_SUPPLIER_ITEM_ASSET_ID + ","
            + SupplierItem.COLUMN_SUPPLIER_ITEM_PRICE + ")VALUES(?,?,?)";
    private String DeleteSupplierItemByIdQuery = "Delete  FROM SupplierItem Where " + SupplierItem.COLUMN_SUPPLIER_ITEM_ID + " = ?";
    private String FindSupplierItemByIdQuery = "SELECT * FROM SupplierItem WHERE " + SupplierItem.COLUMN_SUPPLIER_ITEM_ID + " = ?";
    private String FindAllSupplierQuery = "SELECT * FROM SupplierItem ;";
    private String UpdateSupplier = "Update SupplierItem SET " + SupplierItem.COLUMN_SUPPLIER_ITEM_PRICE + " = ? WHERE " + SupplierItem.COLUMN_SUPPLIER_ITEM_ASSET_ID + " = ? AND " + SupplierItem.COLUMN_SUPPLIER_ITEM_ID + " = ?";

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
    
    public int DeleteSupplierItems(int SupplierId) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(DeleteSupplierItemByIdQuery);
            ps.setInt(1, SupplierId);
            int res = ps.executeUpdate();
            ps.close();
            conn.close();
            return res;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    public int[] UpdateSupplierItems(int s, String[] assetidlist, String[] price) {
        try {
            int[] result;
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(UpdateSupplier);
            for (int i = 0; i < assetidlist.length; i++) {
                int prize = Integer.parseInt(price[i]);
                ps.setDouble(1, prize);
                ps.setInt(2, Integer.parseInt(assetidlist[i]));
                ps.setInt(3, s);
                ps.addBatch();
            }
            result = ps.executeBatch();
            ps.close();
            conn.close();
            return result;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
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

    public SupplierItem GetSupplierItem(int supplierId, int assetId) {
        SupplierItem supplierItem = new SupplierItem();
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM SupplierItem WHERE " + SupplierItem.COLUMN_SUPPLIER_ITEM_ASSET_ID + " = ? AND " + SupplierItem.COLUMN_SUPPLIER_ITEM_ID + " = ?");
            ps.setInt(1, assetId);
            ps.setInt(2, supplierId);

            supplierItem = getResult(ps.executeQuery()).get(0);
            ps.close();
            conn.close();
        } catch (SQLException x) {
            System.err.println(x);
        } catch (IndexOutOfBoundsException x) {
            System.err.println(x);
        }
        return supplierItem;
    }

    private ArrayList<SupplierItem> getResult(ResultSet rs) throws SQLException {
        ArrayList<SupplierItem> suppliers = new ArrayList<SupplierItem>();
        while (rs.next()) {
            SupplierItem e = new SupplierItem();
            e.SupplierId = rs.getInt(SupplierItem.COLUMN_SUPPLIER_ITEM_ID);
            e.AssetId = rs.getInt(SupplierItem.COLUMN_SUPPLIER_ITEM_ASSET_ID);
            e.price = rs.getDouble(SupplierItem.COLUMN_SUPPLIER_ITEM_PRICE);

            e.Supplier = new SupplierService().FindSupplierById(e.SupplierId);
            e.Asset = new AssetService().GetAsset(e.AssetId);
            suppliers.add(e);
        }
        rs.close();
        return suppliers;
    }
}
