package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Java class which accesses the "Income" table in the database
public class MySqlIncomeDao extends MySqlDao implements IncomeDaoInterface{

    @Override
    public List<Income> getAllIncome() throws DaoException
    {
        //initiating variables
        List<Income> incomeList = new ArrayList<>(); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection(); // the ".getConnection()" method is inherited from the "MySqlDao" Java class

            //preparing the SQL query
            String query = "SELECT * FROM Income";
            preparedStatement = connection.prepareStatement(query);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //creating Income type objects by going through the result set of the executed query
            while (resultSet.next()) {

                //stores records in variables
                int expenseId = resultSet.getInt("incomeID");
                String expenseTitle = resultSet.getString("title");
                double expenseAmount = resultSet.getDouble("amount");
                LocalDate expenseDate = resultSet.getDate("dateIncurred").toLocalDate();

                //creates an Income type object
                Income inc = new Income(expenseId, expenseTitle, expenseAmount, expenseDate);
                //adding the Income type object to the list of all expenses
                incomeList.add(inc);
            }
        }
        catch (SQLException e) {  //displays if there is an SQL related issue when executing the code from line 29 to 52
            throw new DaoException("getAllIncomeSet() " + e.getMessage());
        }
        finally //no matter if an exception occurred or not (read about what the 'finally' block means from https://www.baeldung.com/java-finally-keyword)
        {
            try
            {
                if (resultSet != null) {
                    resultSet.close(); //closes the result set
                }
                if (preparedStatement != null) {
                    preparedStatement.close(); //closes the prepared query statement
                }
                if (connection != null) {
                    this.closeConnection(connection); //closes the connection to the database
                }
            }
            catch (SQLException e) { //displays if there is an SQL related issue when closing the result set/prepared query/connection to the database
                throw new DaoException("getAllIncome() " + e.getMessage());
            }
        }

        return incomeList;
    }

    @Override
    public void addNewIncome(Income newIncome) throws DaoException
    {
        //initiating variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "INSERT INTO income Values(null,?,?,?)"; //1st value is null, because it is auto-incremented in the database
            preparedStatement = connection.prepareStatement(query);

            //adding values to the query to complete it
            preparedStatement.setString(1,newIncome.getTitle()); //adds the income's title
            preparedStatement.setDouble(2,newIncome.getAmountEarned()); //adds the income's money amount
            preparedStatement.setDate(3, Date.valueOf(newIncome.getDate())); //adds the income's date of occurrence

            //executing the query
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException("addNewIncomeSet() " + e.getMessage());
        }
        finally
        {
            try
            {
                if (preparedStatement != null) {
                    preparedStatement.close(); //closes the prepared query statement
                }
                if (connection != null) {
                    this.closeConnection(connection); //closes the connection to the database
                }
            }
            catch (SQLException e) {
                throw new DaoException("addNewIncome() " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteExistingIncome(int existingIncomeId) throws DaoException
    {
        //initiating variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "DELETE FROM income WHERE incomeID = ?";
            preparedStatement = connection.prepareStatement(query);

            //adds an expense's id number to complete it
            preparedStatement.setInt(1,existingIncomeId);

            //executing the query
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException("deleteExistingIncomeSet() " + e.getMessage());
        }
        finally
        {
            try
            {
                if (preparedStatement != null) {
                    preparedStatement.close(); //closes the prepared query statement
                }
                if (connection != null) {
                    this.closeConnection(connection); //closes the connection to the database
                }
            }
            catch (SQLException e) {
                throw new DaoException("deleteExistingIncomes() " + e.getMessage());
            }
        }
    }
}
