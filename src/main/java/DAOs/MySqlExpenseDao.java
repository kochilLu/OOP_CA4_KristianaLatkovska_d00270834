package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

//Java class which accesses the "Expense" table in the database
public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface{

    @Override
    public void viewAllExpenses() throws DaoException
    {
        //...
    }

    @Override
    public void addNewExpense(Expense newExpense) throws DaoException
    {
        //...
    }

    @Override
    public void deleteExistingExpense(int existingExpenseId) throws DaoException
    {
        //...
    }
}
