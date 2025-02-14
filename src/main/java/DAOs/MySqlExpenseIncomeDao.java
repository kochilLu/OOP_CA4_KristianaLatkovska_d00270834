package DAOs;

import Exceptions.DaoException;

//Java class which accesses the "Expense" and "Income" tables in the database
public class MySqlExpenseIncomeDao extends MySqlDao implements ExpenseIncomeDaoInterface{

    public void viewAllExpensesAndIncomeOfCertainMonth(int month) throws DaoException
    {
        //...
    }
}
