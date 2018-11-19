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
import objects.ExpenditureLimit;

/**
 *
 * @author RubySenpaii
 */
public class ExpenditureLimitService {

    public int AddExpenditureLimit(ExpenditureLimit limit) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "INSERT INTO ExpenditureLimit (" + ExpenditureLimit.COLUMN_EQUIPMENT + ", " + ExpenditureLimit.COLUMN_SUPPLIES
                    + ", " + ExpenditureLimit.COLUMN_YEAR + ", " + ExpenditureLimit.COLUMN_DIVISION + ", " + ExpenditureLimit.COLUMN_QUARTER + ") "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, limit.Equipment);
            ps.setDouble(2, limit.Supplies);
            ps.setInt(3, limit.Year);
            ps.setString(4, limit.Division);
            ps.setString(5, limit.Quarter);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public ExpenditureLimit GetExpenditureLimitForYear(int year, String division) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM ExpenditureLimit WHERE " + ExpenditureLimit.COLUMN_YEAR + " = ? AND " + ExpenditureLimit.COLUMN_DIVISION + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, year);
            ps.setString(2, division);
            
            ResultSet rs = ps.executeQuery();
            ExpenditureLimit limit = new ExpenditureLimit();
            while (rs.next()) {
                limit.Equipment = rs.getDouble(ExpenditureLimit.COLUMN_EQUIPMENT);
                limit.Supplies = rs.getDouble(ExpenditureLimit.COLUMN_SUPPLIES);
                limit.Year = rs.getInt(ExpenditureLimit.COLUMN_YEAR);
                limit.Division = rs.getString(ExpenditureLimit.COLUMN_DIVISION);
                limit.Quarter = rs.getString(ExpenditureLimit.COLUMN_QUARTER);
            }
            rs.close();
            ps.close();
            con.close();
            return limit;
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        }
    }
}
