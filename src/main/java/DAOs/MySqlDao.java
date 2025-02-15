package DAOs;

import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//class for forming and closing connections to the database
//used code sample 4 provided on Moodle by Dermot Logue
public class MySqlDao {

    //method for forming a connection with the database
    public Connection getConnection() throws DaoException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/expenses_income_database";
        String username = "root";
        String password = "";
        Connection connection = null;

        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Failed to find driver class " + e.getMessage());
            System.exit(1);
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed " + e.getMessage());
            System.exit(2);
        }
        return connection;
    }

    //method for disconnecting from the database
    public void closeConnection(Connection connection) throws DaoException
    {
        try
        {
            if (connection != null) //if a connection is open
            {
                connection.close(); //closes the connection
                connection = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to close the connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
