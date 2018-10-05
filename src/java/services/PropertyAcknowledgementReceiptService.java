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
import objects.PropertyAcknowledgementReceipt;

/**
 *
 * @author RubySenpaii
 */
public class PropertyAcknowledgementReceiptService {

    public int AddPropertyAcknowledgementReceipt(PropertyAcknowledgementReceipt receipt) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "";
            PreparedStatement ps = con.prepareStatement(query);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
}
