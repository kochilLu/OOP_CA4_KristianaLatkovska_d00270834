package DAOs;

import java.util.List;

import DTOs.Expense;
import Exceptions.DaoException;

//interface which defines the methods that can be used by the BusinessObjects
public interface ExpenseDaoInterface {

    public List<Expense> getAllExpenses() throws DaoException;
    //returns a list of Expense objects which refer to all the currently existing expenses on the database

    public void addNewExpense(Expense newExpense) throws DaoException;
    public void deleteExistingExpense(int existingExpenseId) throws DaoException;

}
