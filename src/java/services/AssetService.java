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

/**
 *
 * @author RubySenpaii
 */
public class AssetService {

    public int AddAsset(Asset asset) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "INSERT INTO Asset(" + Asset.COLUMN_ASSET_ID + ", " + Asset.COLUMN_ASSET_NAME + ", "
                    + Asset.COLUMN_DESCRIPTION + ", " + Asset.COLUMN_FUND_CLUSTER + ", " + Asset.COLUMN_STOCK_NO + ", "
                    + Asset.COLUMN_UNIT + ", " + Asset.COLUMN_ASSET_TYPE + ", " + Asset.COLUMN_ESTIMATED_USEFUL_LIFE + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, asset.AssetId);
            ps.setString(2, asset.AssetName);
            ps.setString(3, asset.Description);
            ps.setString(4, "101");
            ps.setString(5, asset.StockNo);
            ps.setString(6, asset.Unit);
            ps.setString(7, asset.AssetType);
            ps.setInt(8, asset.EstimatedUsefulLife);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public int AddAssets(ArrayList<Asset> assets) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            con.setAutoCommit(false);

            String query = "INSERT INTO Asset(" + Asset.COLUMN_ASSET_ID + ", " + Asset.COLUMN_ASSET_NAME + ", "
                    + Asset.COLUMN_DESCRIPTION + ", " + Asset.COLUMN_FUND_CLUSTER + ", " + Asset.COLUMN_STOCK_NO + ", "
                    + Asset.COLUMN_UNIT + ", " + Asset.COLUMN_ASSET_TYPE + ", " + Asset.COLUMN_ESTIMATED_USEFUL_LIFE + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            for (Asset asset : assets) {
                ps.setInt(1, asset.AssetId);
                ps.setString(2, asset.AssetName);
                ps.setString(3, asset.Description);
                ps.setString(4, "101");
                ps.setString(5, asset.StockNo);
                ps.setString(6, asset.Unit);
                ps.setString(7, asset.AssetType);
                ps.setInt(8, asset.EstimatedUsefulLife);
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

    public int UpdateAsset(Asset asset) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "UPDATE Asset SET " + Asset.COLUMN_ASSET_NAME + " = ?, " + Asset.COLUMN_DESCRIPTION + " = ?, "
                    + Asset.COLUMN_FUND_CLUSTER + " = ?, " + Asset.COLUMN_STOCK_NO + " = ?, " + Asset.COLUMN_UNIT + " = ?, "
                    + Asset.COLUMN_ESTIMATED_USEFUL_LIFE + " = ?"
                    + "WHERE " + Asset.COLUMN_ASSET_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, asset.AssetName);
            ps.setString(2, asset.Description);
            ps.setString(3, asset.FundCluster);
            ps.setString(4, asset.StockNo);
            ps.setString(5, asset.Unit);
            ps.setInt(6, asset.EstimatedUsefulLife);
            ps.setInt(7, asset.AssetId);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public Asset GetAsset(int AssetId) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM Asset WHERE " + Asset.COLUMN_ASSET_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, AssetId);

            ArrayList<Asset> assets = getResult(ps.executeQuery());

            ps.close();
            con.close();
            return assets.get(0);
        } catch (SQLException x) {
            System.err.println(x);
            return new Asset();
        }
    }

    public Asset GetAssetLimit(int AssetId) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM ExpenditureItem WHERE " + Asset.COLUMN_ASSET_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, AssetId);
            Asset asset = new Asset();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              
                switch(rs.getString("Division")){
                    case "Admin":
                        asset.AdminQuantityLimit = rs.getInt(asset.COLUMN_QUANTITY_LIMIT);
                        asset.AdminQuantityOrdered = rs.getInt(asset.COLUMN_QUANTITY_ORDERED);
                        break;
                    case "General":
                        asset.GeneralQuantityLimit = rs.getInt(asset.COLUMN_QUANTITY_LIMIT);
                        asset.GeneralQuantityOrdered = rs.getInt(asset.COLUMN_QUANTITY_ORDERED);
                        break;
                    case "Personnel":
                        asset.PersonnelQuantityLimit = rs.getInt(asset.COLUMN_QUANTITY_LIMIT);
                        asset.PersonnelQuantityOrdered = rs.getInt(asset.COLUMN_QUANTITY_ORDERED);
                        break;
                    case "Procurement":
                        asset.ProcurementQuantityLimit = rs.getInt(asset.COLUMN_QUANTITY_LIMIT);
                        asset.ProcurementQuantityOrdered = rs.getInt(asset.COLUMN_QUANTITY_ORDERED);
                        break;
                    case "Records":
                        asset.RecordsQuantityLimit = rs.getInt(asset.COLUMN_QUANTITY_LIMIT);
                        asset.RecordsQuantityOrdered = rs.getInt(asset.COLUMN_QUANTITY_ORDERED);
                        break;
                }
            }
            ps.close();
            con.close();
            return asset;
        } catch (SQLException x) {
            System.err.println(x);
            return new Asset();
        }
    }

    public Asset GetAssetByName(String AssetName) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM Asset WHERE " + Asset.COLUMN_ASSET_NAME + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, AssetName);

            ArrayList<Asset> assets = getResult(ps.executeQuery());

            ps.close();
            con.close();
            return assets.get(0);
        } catch (SQLException x) {
            System.err.println(x);
            return new Asset();
        }
    }

    public ArrayList<Asset> GetAssetsWithType(String type) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM Asset WHERE " + Asset.COLUMN_ASSET_TYPE + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, type);

            ArrayList<Asset> assets = getResult(ps.executeQuery());
            for(Asset asset : assets){
                Asset AssetLimit = new Asset();
                AssetLimit = this.GetAssetLimit(asset.AssetId);
                asset.AdminQuantityLimit = AssetLimit.AdminQuantityLimit;
                asset.GeneralQuantityLimit = AssetLimit.GeneralQuantityLimit;
                asset.PersonnelQuantityLimit = AssetLimit.PersonnelQuantityLimit;
                asset.ProcurementQuantityLimit = AssetLimit.ProcurementQuantityLimit;
                asset.RecordsQuantityLimit = AssetLimit.RecordsQuantityLimit;
                asset.AdminQuantityOrdered = AssetLimit.AdminQuantityOrdered;
                asset.GeneralQuantityOrdered = AssetLimit.GeneralQuantityOrdered;
                asset.PersonnelQuantityOrdered = AssetLimit.PersonnelQuantityOrdered;
                asset.ProcurementQuantityOrdered = AssetLimit.ProcurementQuantityOrdered;
                asset.RecordsQuantityOrdered = AssetLimit.RecordsQuantityOrdered;
            }
            ps.close();
            con.close();
            return assets;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }

    public ArrayList<Asset> GetAssets() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM Asset";
            PreparedStatement ps = con.prepareStatement(query);

            ArrayList<Asset> assets = getResult(ps.executeQuery());

            ps.close();
            con.close();
            return assets;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }

    private ArrayList<Asset> getResult(ResultSet rs) throws SQLException {
        ArrayList<Asset> assets = new ArrayList<>();
        while (rs.next()) {
            Asset asset = new Asset();
            asset.AssetId = rs.getInt(Asset.COLUMN_ASSET_ID);
            asset.AssetName = rs.getString(Asset.COLUMN_ASSET_NAME);
            asset.Description = rs.getString(Asset.COLUMN_DESCRIPTION);
            asset.FundCluster = rs.getString(Asset.COLUMN_FUND_CLUSTER);
            asset.StockNo = rs.getString(Asset.COLUMN_STOCK_NO);
            asset.Unit = rs.getString(Asset.COLUMN_UNIT);
            asset.AssetType = rs.getString(Asset.COLUMN_ASSET_TYPE);
            asset.EstimatedUsefulLife = rs.getInt(Asset.COLUMN_ESTIMATED_USEFUL_LIFE);
            assets.add(asset);
        }
        rs.close();
        return assets;
    }
}
