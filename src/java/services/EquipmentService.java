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
import objects.AssetTracking;
import objects.Equipment;

/**
 *
 * @author RubySenpaii
 */
public class EquipmentService {

    public int AddEquipment(Equipment equipment) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "INSERT INTO Equipment(" + Equipment.COLUMN_ASSET_ID + ", " + Equipment.COLUMN_ASSET_TAG + ", Equipment."
                    + Equipment.COLUMN_CONDITION + ", " + Equipment.COLUMN_DATE_ACQUIRED + ", "
                    + Equipment.COLUMN_FLAG + "," + Equipment.COLUMN_ACQUISITION_COST + ","
                    + Equipment.COLUMN_DESCRIPTION + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, equipment.AssetId);
            ps.setString(2, equipment.AssetTag);
            ps.setString(3, equipment.Condition);
            ps.setObject(4, equipment.DateAcquired);
            ps.setInt(5, equipment.Flag);
            ps.setDouble(6, equipment.AcquisitionCost);
            ps.setString(7, equipment.Description);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public int UpdateEquipment(Equipment equipment) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "UPDATE Equipment SET " + Equipment.COLUMN_ASSET_ID + " = ?, Equipment." + Equipment.COLUMN_CONDITION + " = ?, "
                    + Equipment.COLUMN_DATE_ACQUIRED + " = ?, "
                    + Equipment.COLUMN_FLAG + " = ? WHERE " + Equipment.COLUMN_ASSET_TAG + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, equipment.AssetId);
            ps.setString(2, equipment.Condition);
            ps.setObject(3, equipment.DateAcquired);
            ps.setInt(4, equipment.Flag);
            ps.setString(5, equipment.AssetTag);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public ArrayList<Equipment> GetListOfEquipments() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM Equipment WHERE " + Equipment.COLUMN_ASSET_TAG + " IS NOT NULL";
            PreparedStatement ps = con.prepareStatement(query);
            
            ArrayList<Equipment> equipments = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return equipments;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }
    
    public ArrayList<Equipment> GetListOfEquipmentsWithAssetName(String assetName) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT E.* FROM Equipment E JOIN Asset A ON E.AssetId = A.AssetId WHERE A.AssetName = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetName);
            
            ArrayList<Equipment> equipments = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return equipments;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }
    
    public Equipment GetEquipmentWithAssetTag(String assetTag) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM Equipment WHERE " + Equipment.COLUMN_ASSET_TAG + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);
            
            ArrayList<Equipment> equipments = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return equipments.get(0);
        } catch (SQLException x) {
            System.err.println(x);
            return new Equipment();
        }
    }
    
    private ArrayList<Equipment> getResult(ResultSet rs) throws SQLException {
        ArrayList<Equipment> equipments = new ArrayList<>();
        while (rs.next()) {
            Equipment equipment = new Equipment();
            equipment.AssetId = rs.getInt(Equipment.COLUMN_ASSET_ID);
            equipment.AssetTag = rs.getString(Equipment.COLUMN_ASSET_TAG);
            equipment.Condition = rs.getString(Equipment.COLUMN_CONDITION);
            equipment.DateAcquired = rs.getDate(Equipment.COLUMN_DATE_ACQUIRED);
            equipment.Flag = rs.getInt(Equipment.COLUMN_FLAG);
            equipment.AcquisitionCost = rs.getDouble(Equipment.COLUMN_ACQUISITION_COST);
            equipment.Description = rs.getString(Equipment.COLUMN_DESCRIPTION);
            
            equipment.Asset = new AssetService().GetAsset(equipment.AssetId);
            equipment.CurrentUser = new AssetTrackingService().GetCurrentuser(equipment.AssetTag);
            equipments.add(equipment);
        }
        rs.close();
        return equipments;
    }
}
