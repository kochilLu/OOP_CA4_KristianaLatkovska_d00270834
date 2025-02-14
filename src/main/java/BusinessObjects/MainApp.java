package BusinessObjects;

import DAOs.ExpenseDaoInterface;
import DAOs.MySqlExpenseDao;
import Exceptions.DaoException;

public class MainApp {
    public static void main(String[] args) {

        //testing whether the getAllExpenses() method works

        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();

        try
        {
            System.out.println("List of all expenses: " + IExpenseDao.getAllExpenses());
        }
        catch( DaoException e ) { //displays an error if something goes wrong while executing the 'try' statement
            e.printStackTrace();
        }

    }
}