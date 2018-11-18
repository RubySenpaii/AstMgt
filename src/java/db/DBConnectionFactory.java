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
        String url = "jdbc:mysql://localhost:3306/AstMgt";
        String username="root";
        String password="password";

    public static DBConnectionFactory getInstance(){
        return new DBConnectionFactoryImpl();
    }
    
    public abstract Connection getConnection();
}
