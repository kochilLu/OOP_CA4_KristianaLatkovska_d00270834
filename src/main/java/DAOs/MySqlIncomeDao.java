package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

//Java class which accesses the "Income" table in the database
public class MySqlIncomeDao extends MySqlDao implements IncomeDaoInterface{

    @Override
    public void viewAllIncome() throws DaoException
    {
        //...
    }

    @Override
    public void addNewIncome(Income newIncome) throws DaoException
    {
        //...
    }

    @Override
    public void deleteExistingIncome(int existingIncomeId) throws DaoException
    {
        //...
    }
}
