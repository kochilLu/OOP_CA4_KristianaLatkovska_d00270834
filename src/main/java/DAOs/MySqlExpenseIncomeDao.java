package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Exceptions.DaoException;
import DTOs.Expense;
import DTOs.Income;

//Java class which accesses the "Expense" and "Income" tables in the database
public class MySqlExpenseIncomeDao extends MySqlDao implements ExpenseIncomeDaoInterface{

    @Override
    public List<Object> getAllExpensesAndIncomeOfCertainMonth(int year, int month) throws DaoException
    {
        //initializing the return value
        List<Object> listOfIncomeAndExpenses = new ArrayList<Object>();

        //TODO
        // add to the list expense and income objects

        return listOfIncomeAndExpenses;
    }

    //would it be wiser to store the following methods in MySqlExpenseDao and one in the MySqlIncomeDao? I'll keep them here for now

    //method for getting a list of Expense objects at a certain month
    public List<Expense> getListOfExpensesOfCertainMonth(int year, int month) throws DaoException {
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

    //method for getting a list of Income objects at a certain month

}
