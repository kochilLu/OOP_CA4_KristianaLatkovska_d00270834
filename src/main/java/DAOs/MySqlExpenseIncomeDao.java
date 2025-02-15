package DAOs;

import Exceptions.DaoException;

import java.util.ArrayList;
import java.util.List;

//Java class which accesses the "Expense" and "Income" tables in the database
public class MySqlExpenseIncomeDao extends MySqlDao implements ExpenseIncomeDaoInterface{

    @Override
    public List<Object> getAllExpensesAndIncomeOfCertainMonth(int month) throws DaoException
    {
        //initializing the return value
        List<Object> listOfIncomeAndExpenses = new ArrayList<Object>();

        //TODO
        // add to the list expense and income objects

        return listOfIncomeAndExpenses;
    }

    //method for getting a list of Expense objects at a certain month --> this method should be built in ExpenseDao?

    //method for getting a list of Income objects at a certain month --> this method should be built in IncomeDao?

}
