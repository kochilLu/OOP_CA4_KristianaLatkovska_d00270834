package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.util.List;

//interface which defines the methods that can be used by the BusinessObjects
public interface IncomeDaoInterface {

    List<Income> getAllIncome() throws DaoException;
    //returns a list of Income type objects which refer to all fields in the "Income" table of the database

    void addNewIncome(Income newIncome) throws DaoException;
    void deleteExistingIncome(int existingIncomeId) throws DaoException;

}
