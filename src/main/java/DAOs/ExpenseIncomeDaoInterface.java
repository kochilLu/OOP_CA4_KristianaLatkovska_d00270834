package DAOs;

import Exceptions.DaoException;

import java.util.List;

//interface which defines the methods that can be used by the BusinessObjects
public interface ExpenseIncomeDaoInterface {

    List<Object> getAllExpensesAndIncomeOfCertainMonth(int month) throws DaoException;
    //returns a list of Objects which contain both Income and Expense type objects which have all refer to the same month
}
