package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

//interface which defines the methods that can be used by the BusinessObjects
public interface IncomeDaoInterface {

    public void viewAllIncome() throws DaoException;
    public void addNewIncome(Income newIncome) throws DaoException;
    public void deleteExistingIncome(int existingIncomeId) throws DaoException;

}
