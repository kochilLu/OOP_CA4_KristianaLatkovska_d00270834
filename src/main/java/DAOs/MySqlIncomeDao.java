package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        //...
    }

    @Override
    public void deleteExistingIncome(int existingIncomeId) throws DaoException
    {
        //...
    }
}
