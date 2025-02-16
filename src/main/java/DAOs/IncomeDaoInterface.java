package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.util.List;

//interface which defines the methods that can be used by the BusinessObjects
public interface IncomeDaoInterface {

    List<Income> getAllIncome() throws DaoException;
    //returns a list of Income type objects which refer to all fields in the "Income" table of the database

    void addNewIncome(Income newIncome) throws DaoException;
    //a field in the database's "Income" table is added based on the passed in Income type object

    void deleteExistingIncome(int existingIncomeId) throws DaoException;
    //a field from the database's "Income" table is deleted based on the passed in income id

    List<Income> getListOfIncomeOfCertainMonth(int year, int month) throws DaoException;
    //returns a list of Income objects that refer to income in the database that were incurred at a certain year and month

    List<Integer> getListOfAllIncomeIds() throws DaoException;
    //returns a list of numbers that refer to id's of income on the database

    Income getIncomeById(int incomeId) throws DaoException;
    //returns an income from the database based on its id number
}
