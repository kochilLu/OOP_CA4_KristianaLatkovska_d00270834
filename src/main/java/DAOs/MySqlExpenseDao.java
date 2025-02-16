package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Java class which accesses the "Expense" table in the database
public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface{

    @Override
    public List<Expense> getAllExpenses() throws DaoException
    {
        //initiating variables
        List<Expense> expensesList = new ArrayList<>(); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection(); // the ".getConnection()" method is inherited from the "MySqlDao" Java class

            //preparing the SQL query
            String query = "SELECT * FROM Expenses";
            preparedStatement = connection.prepareStatement(query);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //creating Expense type objects by going through the result set of the executed query
            while (resultSet.next()) {

                //stores records in variables
                int expenseId = resultSet.getInt("expenseID");
                String expenseTitle = resultSet.getString("title");
                String expenseCategory = resultSet.getString("category");
                double expenseAmount = resultSet.getDouble("amount");
                LocalDate expenseDate = resultSet.getDate("dateIncurred").toLocalDate();

                //creates an Expense type object
                Expense exp = new Expense(expenseId, expenseTitle, expenseCategory, expenseAmount, expenseDate);
                //adding hte expense type object to the list of all expenses
                expensesList.add(exp);
            }
        }
        catch (SQLException e) {  //displays if there is an SQL related issue when executing the code from line 29 to 52
            throw new DaoException("getAllExpensesSet() " + e.getMessage());
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
                throw new DaoException("getAllExpenses() " + e.getMessage());
            }
        }

        return expensesList;
    }

    @Override
    public List<Expense> getListOfExpensesOfCertainMonth(int year, int month) throws DaoException {
        //initiating variables
        List<Expense> expensesList = new ArrayList<>(); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "SELECT * FROM Expenses WHERE EXTRACT(YEAR FROM dateIncurred) = ? AND EXTRACT(MONTH FROM dateIncurred) = ?";
            //learned how to get a certain part of the date from https://www.datacamp.com/tutorial/sql-date-greater-than
            //
            preparedStatement = connection.prepareStatement(query);

            //adding the passed in month & year as the missing value in the prepared statement
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, month);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //creating Expense type objects by going through the result set of the executed query
            while(resultSet.next()){

                //stores records in variables
                int expenseId = resultSet.getInt("expenseID");
                String expenseTitle = resultSet.getString("title");
                String expenseCategory = resultSet.getString("category");
                double expenseAmount = resultSet.getDouble("amount");
                LocalDate expenseDate = resultSet.getDate("dateIncurred").toLocalDate();

                //creates an Expense type object
                Expense exp = new Expense(expenseId, expenseTitle, expenseCategory, expenseAmount, expenseDate);
                //adding hte expense type object to the list of all expenses
                expensesList.add(exp);
            }
        }
        catch (SQLException e) {
            throw new DaoException("getListOfExpensesOfCertainMonthSet() " + e.getMessage());
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
                throw new DaoException("getListOfExpensesOfCertainMonth() " + e.getMessage());
            }
        }

        return expensesList;
    }

    @Override
    public List<Integer> getListOfAllExpenseIds() throws DaoException
    {
        //initiating variables
        List<Integer> expenseIdList = new ArrayList<>(); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "SELECT expenseID FROM Expenses";
            preparedStatement = connection.prepareStatement(query);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //storing expense ids by going through the result set of the executed query
            while (resultSet.next()) {
                expenseIdList.add(resultSet.getInt("expenseID"));
            }
        }
        catch (SQLException e) {
            throw new DaoException("getListOfAllExpenseIdsSet() " + e.getMessage());
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
                throw new DaoException("getListOfAllExpenseIds() " + e.getMessage());
            }
        }

        return expenseIdList;
    }

    @Override
    public Expense getExpenseById(int expenseId) throws DaoException
    {
        //initiating variables
        Expense exp = new Expense(expenseId); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "SELECT title, category, amount, dateIncurred FROM Expenses WHERE expenseID = ?";
            preparedStatement = connection.prepareStatement(query);

            //adding an expense id to the prepared query to finish it
            preparedStatement.setInt(1, expenseId);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //adding on to an Expense object by going through the result set of the executed query
            while (resultSet.next()) {

                //stores records in variables
                String expenseTitle = resultSet.getString("title");
                String expenseCategory = resultSet.getString("category");
                double expenseAmount = resultSet.getDouble("amount");
                LocalDate expenseDate = resultSet.getDate("dateIncurred").toLocalDate();

                //sets the stored values to the previously declared return value
                exp.setTitle(expenseTitle);
                exp.setCategory(expenseCategory);
                exp.setAmountSpent(expenseAmount);
                exp.setDate(expenseDate);
            }
        }
        catch (SQLException e) {
            throw new DaoException("getExpenseByIdSet() " + e.getMessage());
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
                throw new DaoException("getExpenseById() " + e.getMessage());
            }
        }

        return exp;
    }

    @Override
    public void addNewExpense(Expense newExpense) throws DaoException
    {
        //initiating variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "INSERT INTO expenses Values(null,?,?,?,?)"; //1st value is null, because it is auto-incremented in the database
            preparedStatement = connection.prepareStatement(query);

            //adding values to the query to complete it
            preparedStatement.setString(1,newExpense.getTitle()); //adds the expense's title
            preparedStatement.setString(2,newExpense.getCategory()); //adds the expense's category
            preparedStatement.setDouble(3,newExpense.getAmountSpent()); //adds the expense's money amount
            preparedStatement.setDate(4, Date.valueOf(newExpense.getDate())); //adds the expense's date of occurrence

            //executing the query
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException("addNewExpenseSet() " + e.getMessage());
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
                throw new DaoException("addNewExpense() " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteExistingExpense(int existingExpenseId) throws DaoException
    {
        //initiating variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "DELETE FROM expenses WHERE expenseID = ?";
            preparedStatement = connection.prepareStatement(query);

            //adds an expense's id number to complete it
            preparedStatement.setInt(1,existingExpenseId);

            //executing the query
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException("deleteExistingExpenseSet() " + e.getMessage());
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
                throw new DaoException("deleteExistingExpense() " + e.getMessage());
            }
        }
    }
}
