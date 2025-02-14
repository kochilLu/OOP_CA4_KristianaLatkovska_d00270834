package DAOs;

import Exceptions.DaoException;

//interface which defines the methods that can be used by the BusinessObjects
public interface ExpenseIncomeDaoInterface {

    public void viewAllExpensesAndIncomeOfCertainMonth(int month) throws DaoException;

}
