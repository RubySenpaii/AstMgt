/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Bryll Joey Delfin
 */
public class EmployeeService {
    public boolean Authenticate (String username , String password){
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("");
        } catch (SQLException e) {
            System.err.println(e);
        }
        return false;
    }
    
    public boolean AddEmployee (String username , String password){
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("");
        } catch (SQLException e) {
            System.err.println(e);
        }
        return false;
    }
}
