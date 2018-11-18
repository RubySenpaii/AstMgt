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
                    + Equipment.COLUMN_CONDITION + ", " + Equipment.COLUMN_DATE_ACQUIRED + ", " + Equipment.COLUMN_ESTIMATED_USEFUL_LIFE + ", "
                    + Equipment.COLUMN_FLAG + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, equipment.AssetId);
            ps.setString(2, equipment.AssetTag);
            ps.setString(3, equipment.Condition);
            ps.setObject(4, equipment.DateAcquired);
            ps.setInt(5, equipment.EstimatedUsefulLife);
            ps.setInt(6, equipment.Flag);

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
            
            String query = "UPDATE Equipment SET " + Equipment.COLUMN_ASSET_ID + " = ?, " + Equipment.COLUMN_CONDITION + " = ?, "
                    + Equipment.COLUMN_DATE_ACQUIRED + " = ?, " + Equipment.COLUMN_ESTIMATED_USEFUL_LIFE + " = ?, "
                    + Equipment.COLUMN_FLAG + " WHERE " + Equipment.COLUMN_ASSET_TAG + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, equipment.AssetId);
            ps.setString(2, equipment.Condition);
            ps.setObject(3, equipment.DateAcquired);
            ps.setInt(4, equipment.EstimatedUsefulLife);
            ps.setInt(5, equipment.Flag);
            ps.setString(6, equipment.AssetTag);
            
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
            equipment.EstimatedUsefulLife = rs.getInt(Equipment.COLUMN_ESTIMATED_USEFUL_LIFE);
            equipment.Flag = rs.getInt(Equipment.COLUMN_FLAG);
            
            equipment.Asset = new AssetService().GetAsset(equipment.AssetId);
            equipment.CurrentUser = new AssetTrackingService().GetCurrentuser(equipment.AssetTag);
            equipments.add(equipment);
        }
        rs.close();
        return equipments;
    }
}
