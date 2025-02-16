package DAOs;

import DTOs.YearMonth;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpenseIncomeDao extends MySqlDao implements ExpenseIncomeDaoInterface{

    @Override
    public List<YearMonth> getListOfDistinctYearMonthsOfExpensesOrIncome(String tableName) throws DaoException
    {
        //initiating variables
        List<YearMonth> yearMonthList = new ArrayList<>(); //return value
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            //connects to the database
            connection = this.getConnection();

            //preparing the SQL query
            String query = "SELECT DISTINCT MONTH(dateIncurred) AS `Month`, YEAR(dateIncurred) AS `Year` FROM ? GROUP BY MONTH(dateIncurred), YEAR(dateIncurred);";
            //query from Emulite (2019) on https://stackoverflow.com/questions/50775116/mysql-query-select-distinct-month-and-year
            //
            preparedStatement = connection.prepareStatement(query);

            //adding the table name to complete the query
            preparedStatement.setString(1, tableName);

            //executing the query
            resultSet = preparedStatement.executeQuery();

            //creating YearMonth type objects by going through the result set of the executed query and adding them to a list
            while(resultSet.next()){

                //stores records in a variables
                int month = resultSet.getInt("Month");
                int year = resultSet.getInt("Year");

                //creates a YearMonth type object
                YearMonth ym = new YearMonth(year, month);
                //adding the YearMonth type object to the list of all YearMonths
                yearMonthList.add(ym);
            }
        }
        catch (SQLException e) {
            throw new DaoException("getListOfDistinctYearMonthsOfExpensesOrIncomeSet() " + e.getMessage());
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
                throw new DaoException("getListOfDistinctYearMonthsOfExpensesOrIncome() " + e.getMessage());
            }
        }

        return yearMonthList;
    }
}