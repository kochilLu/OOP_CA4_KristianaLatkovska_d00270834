package DAOs;

import java.util.List;

import DTOs.Expense;
import Exceptions.DaoException;

//interface which defines the methods that can be used by the BusinessObjects
public interface ExpenseDaoInterface {

    List<Expense> getAllExpenses() throws DaoException;
    //returns a list of Expense objects which refer to all the currently existing expenses on the database

    void addNewExpense(Expense newExpense) throws DaoException;
    //a field in the database's "Expenses" table is added based on the passed in Expense type object

    void deleteExistingExpense(int existingExpenseId) throws DaoException;
    //a field from the database's "Expenses" table is deleted based on the passed in expense id
}
