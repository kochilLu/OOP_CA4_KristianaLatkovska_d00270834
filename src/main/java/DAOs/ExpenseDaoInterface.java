package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

//interface which defines the methods that can be used by the BusinessObjects
public interface ExpenseDaoInterface {

    public void viewAllExpenses() throws DaoException;
    public void addNewExpense(Expense newExpense) throws DaoException;
    public void deleteExistingExpense(int existingExpenseId) throws DaoException;

}
