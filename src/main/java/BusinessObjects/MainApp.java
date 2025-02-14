package BusinessObjects;

import java.util.List;

import DAOs.ExpenseDaoInterface;
import DAOs.MySqlExpenseDao;
import DTOs.Expense;
import Exceptions.DaoException;

public class MainApp {

    public static void main(String[] args) {

        //testing whether the displayAllExpenses(List<Expense> expenseList) method works

        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();

        try
        {
            displayAllExpenses(IExpenseDao.getAllExpenses());

        }
        catch( DaoException e ) { //displays an error if something goes wrong while executing the 'try' statement
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // methods for displaying information neatly
    //------------------------------------------------------------------------------------------------------------------
    //method for neatly displaying a list of Expense objects
    public static void displayAllExpenses(List<Expense> expenseList)
    {
        System.out.println("\nEXPENSES:");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Id\tTitle\t\t\t\t\tCategory\t\tAmount Spent\tDate");

        for(Expense exp : expenseList) //goes through the list of Expense type objects
        {
            System.out.printf("%d\t%-20s\t%-15s\t%-12.2f\t%s\n", exp.getId(), exp.getTittle(), exp.getCategory(), exp.getAmountSpent(), exp.getDate());
        }

        System.out.println("-----------------------------------------------------------------------");
    }

}