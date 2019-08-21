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
import objects.AssetIncident;

/**
 *
 * @author RubySenpaii
 */
public class AssetIncidentService {
    
    public int AddAssetIncident(AssetIncident incident) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "INSERT INTO AssetIncident (" + AssetIncident.COLUMN_ASSET_TAG + ", " + AssetIncident.COLUMN_REMARKS + ", "
                    + AssetIncident.COLUMN_REPORTED_BY + ", " + AssetIncident.COLUMN_TIMESTAMP + ", " + AssetIncident.COLUMN_SEVERITY + ") "
                    + "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, incident.AssetTag);
            ps.setString(2, incident.Remarks);
            ps.setInt(3, incident.ReportedBy);
            ps.setObject(4, incident.Timestamp);
            ps.setInt(5, incident.Severity);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public int UpdateAssetIncident(AssetIncident incident) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "UPDATE AssetIncident SET " + AssetIncident.COLUMN_REMARKS + " = ?, " 
                    + AssetIncident.COLUMN_SEVERITY + " = ? "
                    + "WHERE " + AssetIncident.COLUMN_ASSET_TAG + " = ? AND " + AssetIncident.COLUMN_TIMESTAMP + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, incident.Remarks);
            ps.setInt(2, incident.Severity);
            ps.setString(3, incident.AssetTag);
            ps.setObject(4, incident.Timestamp);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public ArrayList<AssetIncident> GetPendingIncidents() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM AssetIncident WHERE Severity = 0";
            PreparedStatement ps = con.prepareStatement(query);
            
            ArrayList<AssetIncident> incidents = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return incidents;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }
    
    public ArrayList<AssetIncident> GetIncidentsOfAsset(String assetTag) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM AssetIncident WHERE AssetTag = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);
            
            ArrayList<AssetIncident> incidents = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return incidents;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }
    
    public AssetIncident FindAssetIncidentDetails(String assetTag, String timestamp) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM AssetIncident WHERE AssetTag = ? AND Timestamp = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);
            ps.setString(2, timestamp);
            
            ArrayList<AssetIncident> incidents = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return incidents.get(0);
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        }
    }
    
    public AssetIncident GetIncidentsOfAssetCount(String assetTag) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT AI.AssetTag, AI.Severity, COUNT(AI.AssetTag) AS 'AssetCount' FROM AssetIncident AI "
                    + "WHERE AI.AssetTag = ? GROUP BY AI.AssetTag, AI.Severity";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);
            
            AssetIncident incident = new AssetIncident();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                incident.AssetTag = rs.getString(AssetIncident.COLUMN_ASSET_TAG);
                switch (rs.getInt(AssetIncident.COLUMN_SEVERITY)) {
                    case 1: incident.lowCount = rs.getInt("AssetCount"); break;
                    case 2: incident.mediumCount = rs.getInt("AssetCount"); break;
                    case 3: incident.highCount = rs.getInt("AssetCount"); break;
                }
            }
            ps.close();
            con.close();
            return incident;
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        }
    }
    
    public ArrayList<AssetIncident> getResult(ResultSet rs) throws SQLException {
        ArrayList<AssetIncident> incidents = new ArrayList<>();
        while (rs.next()) {
            AssetIncident incident = new AssetIncident();
            incident.AssetTag = rs.getString(AssetIncident.COLUMN_ASSET_TAG);
            incident.Remarks = rs.getString(AssetIncident.COLUMN_REMARKS);
            incident.ReportedBy = rs.getInt(AssetIncident.COLUMN_REPORTED_BY);
            incident.Timestamp = rs.getDate(AssetIncident.COLUMN_TIMESTAMP);
            incident.Severity = rs.getInt(AssetIncident.COLUMN_SEVERITY);
            incidents.add(incident);
        }
        rs.close();
        return incidents;
    }
}
