package dbconnection;
// http://www.java2s.com/Code/Java/Database-SQL-JDBC/CatalogDatabase-SQL-JDBC.htm
//https://www.softwaretestinghelp.com/jdbc-resultset-tutorial/
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class DbTest4 {
    //Connection object
    static Connection conn = null;
    //Statement object
    private static Statement stmt = null;
    //Constant for Database URL
    public static String DB_URL = "jdbc:mysql://localhost:3306/skillo_db";
    //Constant for Database Username
    public static String USER = "root";
    //Constant for Database Password
    public static String PASS = "123456789";

    @BeforeTest
    public void setUp() throws Exception{
        try {
            //Make the database connection
            String dbClass = "com.mysql.cj.jdbc.Driver";
            Class.forName(dbClass).newInstance();
            //Get connection to DB
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //Statement object to send the SQL statement to the Database
            stmt = conn.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
            String query = "SELECT * FROM factory";
            // Get the contents of user table from DB
            ResultSet res = stmt.executeQuery(query);
            int numColumns = 0;
            while (res.next()) {
                numColumns = res.getMetaData().getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    System.out.println("--------------------");
                    System.out.println("COLUMN " + i + " --> " + res.getObject(i));
                }
            }
            Assert.assertEquals((numColumns), 2);
            System.out.println("The total number of columns in this table is " + numColumns);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() throws Exception{
        //Close DB connection
        if (stmt !=null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }

    }
}
