package DAOs;

import DTOs.Expense;
import DTOs.Income;
import Exceptions.DaoException;

import java.util.List;

//interface which defines the methods that can be used by the BusinessObjects
public interface ExpenseIncomeDaoInterface {

    List<Object> getAllExpensesAndIncomeOfCertainMonth(int year, int month) throws DaoException;
    //returns a list of Objects which contain both Income and Expense type objects which have all refer to the same month

    //public List<Expense> getListOfExpensesOfCertainMonth(int year, int month) throws DaoException;
    public List<Income> getListOfIncomeOfCertainMonth(int year, int month) throws DaoException;
    //temporarily here, just for a test
}
