// reality
package db;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDB {

    public User authenticate(User user) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from users where username = ? and password=password(?) ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            ResultSet rs = pstmt.executeQuery();
            User tempUser = null;
            if (rs.next()) {
                tempUser = new User();
                tempUser.setUsername(user.getUsername());          
                tempUser.setName(rs.getString("name"));
                tempUser.setGroup(rs.getString("role"));

            }
            pstmt.close();
            rs.close();
            conn.close();
            return tempUser;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
    public boolean register(User user){
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            
            String query = "insert into Users values (?,password(?),?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(4, user.getName());
            pstmt.setString(5, user.getGroup());
            int isSuccess = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return isSuccess==1;
        }
         catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
