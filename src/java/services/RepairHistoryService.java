/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnectionFactory;
import java.sql.Connection;
import objects.RepairHistory;

/**
 *
 * @author RubySenpaii
 */
public class RepairHistoryService {

    public int AddRepairLog(RepairHistory log) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "INSERT INTO RepairHistory(" + RepairHistory.COLUMN_APPROVED_BY + ", " + RepairHistory.COLUMN_APPROVED_DATE + ", "
                    + RepairHistory.COLUMN_ARTICLE + ", " + RepairHistory.COLUMN_ASSET_TAG + ", " + RepairHistory.COLUMN_COST + ", "
                    + RepairHistory.COLUMN_REQUESTED_BY + ", " + RepairHistory.COLUMN_REQUESTED_DATE + ") "
                    + "VALUES (?, ?, ?, ?, ?, ?"
        }
    }
}
