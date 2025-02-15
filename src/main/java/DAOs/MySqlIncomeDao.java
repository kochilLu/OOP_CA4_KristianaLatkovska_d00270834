package DAOs;

import DTOs.Expense;
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
    public List<Income> getListOfIncomeOfCertainMonth(int year, int month) throws DaoException {
        //initiating variables
        List<Income> incomeList = new ArrayList<>(); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "SELECT * FROM Income WHERE EXTRACT(YEAR FROM dateIncurred) = ? AND EXTRACT(MONTH FROM dateIncurred) = ?";
            //learned how to get a certain part of the date from https://www.datacamp.com/tutorial/sql-date-greater-than
            //
            preparedStatement = connection.prepareStatement(query);

            //adding the passed in month & year as the missing value in the prepared statement
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, month);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //creating Income type objects by going through the result set of the executed query
            while (resultSet.next()) {

                //stores records in variables
                int incomeId = resultSet.getInt("incomeID");
                String incomeTitle = resultSet.getString("title");
                double incomeAmount = resultSet.getDouble("amount");
                LocalDate incomeDate = resultSet.getDate("dateIncurred").toLocalDate();

                //creates an Income type object
                Income inc = new Income(incomeId, incomeTitle, incomeAmount, incomeDate);
                //adding the Income type object to the list of all expenses
                incomeList.add(inc);
            }
        }
        catch (SQLException e) {
            throw new DaoException("getListOfIncomeOfCertainMonthSet() " + e.getMessage());
        }
        finally
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
            catch (SQLException e) {
                throw new DaoException("getListOfIncomeOfCertainMonth() " + e.getMessage());
            }
        }

        return incomeList;
    }

    @Override
    public List<Integer> getListOfAllIncomeIds() throws DaoException
    {
        //initiating variables
        List<Integer> incomeIdList = new ArrayList<>(); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "SELECT incomeID FROM Income";
            preparedStatement = connection.prepareStatement(query);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //storing income ids by going through the result set of the executed query
            while (resultSet.next()) {
                incomeIdList.add(resultSet.getInt("incomeID"));
            }
        }
        catch (SQLException e) {
            throw new DaoException("getListOfAllIncomeIdsSet() " + e.getMessage());
        }
        finally
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
            catch (SQLException e) {
                throw new DaoException("getListOfAllIncomeIds() " + e.getMessage());
            }
        }

        return incomeIdList;
    }

    @Override
    public Income getIncomeById(int incomeId) throws DaoException
    {
        //initiating variables
        Income inc = new Income(incomeId); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "SELECT title, amount, dateIncurred FROM Income WHERE incomeID = ?";
            preparedStatement = connection.prepareStatement(query);

            //adding an income id to the prepared query to finish it
            preparedStatement.setInt(1, incomeId);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //adding on to an Income object by going through the result set of the executed query
            while (resultSet.next()) {

                //stores records in variables
                String incomeTitle = resultSet.getString("title");
                double incomeAmount = resultSet.getDouble("amount");
                LocalDate incomeDate = resultSet.getDate("dateIncurred").toLocalDate();

                //sets the stored values to the previously declared return value
                inc.setTitle(incomeTitle);
                inc.setAmountEarned(incomeAmount);
                inc.setDate(incomeDate);
            }
        }
        catch (SQLException e) {
            throw new DaoException("getIncomeByIdSet() " + e.getMessage());
        }
        finally
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
            catch (SQLException e) {
                throw new DaoException("getIncomeById() " + e.getMessage());
            }
        }

        return inc;
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

            //adds an income's id number to complete it
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
