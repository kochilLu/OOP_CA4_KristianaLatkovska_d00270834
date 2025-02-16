package DAOs;

import DTOs.YearMonth;
import DTOs.Expense;
import Exceptions.DaoException;

import java.util.List;

//interface which defines the methods that can be used by the BusinessObjects
public interface ExpenseDaoInterface {

    List<Expense> getAllExpenses() throws DaoException;
    //returns a list of Expense objects which refer to all the currently existing expenses on the database

    void addNewExpense(Expense newExpense) throws DaoException;
    //a field in the database's "Expenses" table is added based on the passed in Expense type object

    void deleteExistingExpense(int existingExpenseId) throws DaoException;
    //a field from the database's "Expenses" table is deleted based on the passed in expense id

    List<Expense> getListOfExpensesOfCertainMonth(int year, int month) throws DaoException;
    //returns a list of Expense objects that refer to expenses in the database that were incurred at a certain year and month

    List<Integer> getListOfAllExpenseIds() throws DaoException;
    //returns a list of numbers that refer to id's of expenses on the database

    Expense getExpenseById(int expenseId) throws DaoException;
    //returns an expense from the database based on its id number
}