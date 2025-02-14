package BusinessObjects;

import java.time.LocalDate;
import java.util.List;

import DAOs.ExpenseDaoInterface;
import DAOs.MySqlExpenseDao;
import DTOs.Expense;
import Exceptions.DaoException;

public class MainApp {

    public static void main(String[] args) {

        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();

        try
        {
            //viewing all expenses
            //System.out.println("List of all expenses: " + IExpenseDao.getAllExpenses());
            displayAllExpenses(IExpenseDao.getAllExpenses());

            //adding a new expense
            //Expense exp = new Expense("Chocolate", "Gift", 11.28, LocalDate.of(2025,2,12)); //learned about LocalDate here: https://www.baeldung.com/java-creating-localdate-with-values
            //IExpenseDao.addNewExpense(exp);
            //checking to see any changes
            //displayAllExpenses(IExpenseDao.getAllExpenses());

            //deleting an existing expense
            IExpenseDao.deleteExistingExpense(6);
            //checking to see any changes
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
        System.out.println("-----------------------------------------------------------------------");

        for(Expense exp : expenseList) //goes through the list of Expense type objects
        {
            System.out.printf("%d\t%-20s\t%-15s\t%-12.2f\t%s\n", exp.getId(), exp.getTittle(), exp.getCategory(), exp.getAmountSpent(), exp.getDate());
        }

        System.out.println("-----------------------------------------------------------------------");
    }
}