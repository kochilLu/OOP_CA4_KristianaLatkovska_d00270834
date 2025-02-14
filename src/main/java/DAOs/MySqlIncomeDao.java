package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

//Java class which accesses the "Income" table in the database
public class MySqlIncomeDao extends MySqlDao implements IncomeDaoInterface{

    public void viewAllIncome() throws DaoException
    {
        //...
    }

    public void addNewIncome(Income newIncome) throws DaoException
    {
        //...
    }

    public void deleteExistingIncome(int existingIncomeId) throws DaoException
    {
        //...
    }
}
