package BusinessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        //testing whether the pom.xml file works:
        //if it is possible to connect to the mySql server on my device
        Main app = new Main();
        app.start();
    }

    //using the code provided in sample 1 by Dermot Logue on Moodle to form a connection test
    public void start(){
        System.out.println("\nSample 1 - Connecting to MySQL Database called \"test\" using MySQL JDBC Driver");

        String url = "jdbc:mysql://localhost/"; // location of database
        String dbName = "connectjdbexample";     // database name
        String userName = "root";   // default
        String password = "";       // default

        ///  Attempt to connect to the database using the credentials supplied
        try ( Connection conn =
                      DriverManager.getConnection(url + dbName, userName, password) )
        {
            System.out.println("SUCCESS ! - Your program has successfully connected to the MySql Database Server. Well done.");
            System.out.println("... we could query the database using the SQL commands you learned in DBMS...");
            System.out.println("... but for now, we will simply close the connection.");

            System.out.println("Your program is disconnecting from the database - goodbye.");
        }
        catch (SQLException ex)
        {
            /// If the attempt to connect fails, and exception is thrown we end up here in
            /// the  catch block (the exception handler)
            System.out.println("Failed to connect to database - check that you have started the MySQL from XAMPP, and that your connection details are correct.");
            ex.printStackTrace();
        }
    }
}