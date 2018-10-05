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
                    + Asset.COLUMN_UNIT + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, asset.AssetId);
            ps.setString(2, asset.AssetName);
            ps.setString(3, asset.Description);
            ps.setString(4, asset.FundCluster);
            ps.setString(5, asset.StockNo);
            ps.setString(6, asset.Unit);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
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
                    + Asset.COLUMN_FUND_CLUSTER + " = ?, " + Asset.COLUMN_STOCK_NO + " = ?, " + Asset.COLUMN_UNIT + " = ? "
                    + "WHERE " + Asset.COLUMN_ASSET_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, asset.AssetName);
            ps.setString(2, asset.Description);
            ps.setString(3, asset.FundCluster);
            ps.setString(4, asset.StockNo);
            ps.setString(5, asset.Unit);
            ps.setInt(6, asset.AssetId);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x ) {
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
            assets.add(asset);
        }
        rs.close();
        return assets;
    }
}
