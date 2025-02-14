package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

//Java class which accesses the "Expense" table in the database
public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface{

    public void viewAllExpenses() throws DaoException
    {
        //...
    }

    public void addNewExpense(Expense newExpense) throws DaoException
    {
        //...
    }

    public void deleteExistingExpense(int existingExpenseId) throws DaoException
    {
        //...
    }
}
