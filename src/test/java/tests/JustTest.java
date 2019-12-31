package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;


public class JustTest extends TestBase {

    String dbUrl = "jdbc:mysql://remotemysql.com:3306/JAnKQvHKyP";
    String username = "JAnKQvHKyP";
    String password = "3TG9GLiMPh";
    String query = "select *  from users where id = 1";


    @Test
    public void userCanAddToLocalStorage() throws InterruptedException {
        try {
            String user  =  db_connection();
            Assert.assertTrue(user.contains("testing@gmail.com"));
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String db_connection() throws SQLException {
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        String email= null ;
        while (rs.next()) {
        	email = rs.getString("username");
        }
        con.close();
        return email;
    }

}
