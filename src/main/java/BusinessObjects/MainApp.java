package BusinessObjects;

import java.time.LocalDate;
import java.util.List;

import DAOs.ExpenseDaoInterface;
import DAOs.IncomeDaoInterface;
import DAOs.MySqlExpenseDao;
import DAOs.MySqlIncomeDao;
import DTOs.Expense;
import DTOs.Income;
import Exceptions.DaoException;

public class MainApp {

    public static void main(String[] args) {

        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();
        IncomeDaoInterface IIncomeDao = new MySqlIncomeDao();

        try
        {
            //assuming that all teh passed in parameters are valid values, tests:
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //EXPENSES
            //viewing all expenses
            //System.out.println("List of all expenses: " + IExpenseDao.getAllExpenses());
            //displayAllExpenses(IExpenseDao.getAllExpenses());

            //adding a new expense
            //Expense exp = new Expense("Chocolate", "Gift", 11.28, LocalDate.of(2025,2,12)); //learned about LocalDate here: https://www.baeldung.com/java-creating-localdate-with-values
            //IExpenseDao.addNewExpense(exp);
            //checking to see any changes
            //displayAllExpenses(IExpenseDao.getAllExpenses());

            //deleting an existing expense
            //IExpenseDao.deleteExistingExpense(6);
            //checking to see any changes
            //displayAllExpenses(IExpenseDao.getAllExpenses());
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //INCOME
            //viewing all income
            //System.out.println("List of all income: " + IIncomeDao.getAllIncome());
            displayAllIncome(IIncomeDao.getAllIncome());



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

    //method for neatly displaying a list of Income objects
    public static void displayAllIncome(List<Income> incomeList)
    {
        System.out.println("\nINCOME:");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Id\tTitle\t\t\t\t\tAmount Earned\tDate");
        System.out.println("-----------------------------------------------------------------------");

        for(Income inc : incomeList) //goes through the list of Income type objects
        {
            System.out.printf("%d\t%-20s\t%-12.2f\t%s\n", inc.getId(), inc.getTitle(), inc.getAmountEarned(), inc.getDate());
        }

        System.out.println("-----------------------------------------------------------------------");
    }

}