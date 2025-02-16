package DAOs;

import DTOs.YearMonth;
import Exceptions.DaoException;

import java.util.List;

//this in the main app is used to validate specific user input and make it easier for the user to interact with the main application
public interface ExpenseIncomeDaoInterface {

    List<YearMonth> getListOfDistinctYearMonthsOfExpensesOrIncome(String tableName) throws DaoException;
    //returns a list of YearMonth type objects which refer to entries
    // in the database "Expenses" or "Income" table (based on tableName chosen) distinct combination of year and month
}
