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
import objects.Asset;
import objects.AssetRequested;

/**
 *
 * @author RubySenpaii
 */
public class AssetRequestedService {

    public int AddAssetRequest(ArrayList<AssetRequested> assetsRequested) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            con.setAutoCommit(false);

            String query = "INSERT INTO AssetRequested (" + AssetRequested.COLUMN_ASSET_ID + ", " + AssetRequested.COLUMN_PURCHASE_REQUEST_ID + ", "
                    + AssetRequested.COLUMN_QUANTITY + ", " + AssetRequested.COLUMN_UNIT_COST + ", " + AssetRequested.COLUMN_QUANTITY_REFUNDED + ") "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            for (AssetRequested assetRequested : assetsRequested) {
                ps.setInt(1, assetRequested.AssetId);
                ps.setInt(2, assetRequested.PurchaseRequestId);
                ps.setInt(3, assetRequested.Quantity);
                ps.setDouble(4, assetRequested.UnitCost);
                ps.setInt(5, assetRequested.QuantityRefunded);
                ps.addBatch();
            }

            int[] result = ps.executeBatch();
            con.commit();
            ps.close();
            con.close();
            System.out.println("HELLO PO");
            System.out.println(result.length);
            return result.length;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public int UpdateAssetRequest(ArrayList<AssetRequested> assetsRequested) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            con.setAutoCommit(false);

            String query = "UPDATE AssetRequested SET " + AssetRequested.COLUMN_QUANTITY + " = ?, "
                    + AssetRequested.COLUMN_QUANTITY_REFUNDED + " = ?, " + AssetRequested.COLUMN_UNIT_COST + " = ? "
                    + "WHERE " + AssetRequested.COLUMN_PURCHASE_REQUEST_ID + " = ? AND " + AssetRequested.COLUMN_ASSET_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            for (AssetRequested assetRequested : assetsRequested) {
                ps.setInt(1, assetRequested.Quantity);
                ps.setInt(2, assetRequested.QuantityRefunded);
                ps.setDouble(3, assetRequested.UnitCost);
                ps.setInt(4, assetRequested.PurchaseRequestId);
                ps.setInt(5, assetRequested.AssetId);
                ps.addBatch();
            }

            int[] result = ps.executeBatch();
            con.commit();
            ps.close();
            con.close();
            return result.length;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public ArrayList<AssetRequested> GetAssetsRequested() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM AssetRequested";
            PreparedStatement ps = con.prepareCall(query);

            ArrayList<AssetRequested> assetsRequested = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return assetsRequested;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }

    public ArrayList<AssetRequested> GetAssetsRequestedWithPurchaseRequest(int purchaseRequestId) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM AssetRequested WHERE " + AssetRequested.COLUMN_PURCHASE_REQUEST_ID + " = ?";
            PreparedStatement ps = con.prepareCall(query);
            ps.setInt(1, purchaseRequestId);

            ArrayList<AssetRequested> assetsRequested = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return assetsRequested;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }

    public ArrayList<AssetRequested> GetRefundList() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT A.AssetName, AR.Quantity, AR.QuantityRefunded, AR.UnitCost, PO.PurchaseOrderNo "
                    + "FROM AssetRequested AR JOIN PurchaseRequest PR ON AR.PurchaseRequestId = PR.PurchaseRequestId "
                    + "                     JOIN PurchaseOrder PO ON PR.PurchaseRequestId = PO.PurchaseRequestId "
                    + "                     JOIN Asset A ON AR.AssetId = A.AssetId "
                    + "WHERE AR.QuantityRefunded > 0";
            PreparedStatement ps = con.prepareCall(query);

            ArrayList<AssetRequested> assetsRequested = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AssetRequested assetRequested = new AssetRequested();
                assetRequested.AssetName = rs.getString("AssetName");
                assetRequested.PurchaseOrderNumber = rs.getString("PurchaseOrderNo");
                assetRequested.Quantity = rs.getInt(AssetRequested.COLUMN_QUANTITY);
                assetRequested.UnitCost = rs.getDouble(AssetRequested.COLUMN_UNIT_COST);
                assetRequested.QuantityRefunded = rs.getInt(AssetRequested.COLUMN_QUANTITY_REFUNDED);
                assetsRequested.add(assetRequested);
            }
            ps.close();
            con.close();
            return assetsRequested;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }

    private ArrayList<AssetRequested> getResult(ResultSet rs) throws SQLException {
        ArrayList<AssetRequested> assetsRequested = new ArrayList<>();
        while (rs.next()) {
            AssetRequested assetRequested = new AssetRequested();
            assetRequested.AssetId = rs.getInt(AssetRequested.COLUMN_ASSET_ID);
            assetRequested.PurchaseRequestId = rs.getInt(AssetRequested.COLUMN_PURCHASE_REQUEST_ID);
            assetRequested.Quantity = rs.getInt(AssetRequested.COLUMN_QUANTITY);
            assetRequested.UnitCost = rs.getDouble(AssetRequested.COLUMN_UNIT_COST);
            assetRequested.QuantityRefunded = rs.getInt(AssetRequested.COLUMN_QUANTITY_REFUNDED);

            assetRequested.Asset = new AssetService().GetAsset(assetRequested.AssetId);
            assetsRequested.add(assetRequested);
        }
        rs.close();
        return assetsRequested;
    }
}
