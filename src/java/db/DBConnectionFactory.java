//reality

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;

/**
 *
 * @author Garfield
 */
public abstract class DBConnectionFactory {
        String url = "jdbc:mysql://localhost/SRA";
        String username="root";
        String password="delfin";

    public static DBConnectionFactory getInstance(){
        return new DBConnectionFactoryImpl();
    }
    
    public abstract Connection getConnection();
}
