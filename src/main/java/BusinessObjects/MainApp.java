package BusinessObjects;

import java.time.LocalDate;
import java.util.List;

import DAOs.*;
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
            displayAllExpenses(IExpenseDao.getAllExpenses());
            //displaying the total money spent on all expenses
            //System.out.println("Total money spent on all expenses: " + getExpensesAmount(IExpenseDao.getAllExpenses()));

            //viewing all expense ids
            System.out.println("IDs of existing expenses on the database: " + IExpenseDao.getListOfAllExpenseIds());

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
            //displayAllIncome(IIncomeDao.getAllIncome());
            //displaying the total money earned from all the income
            //System.out.println("Total money earned: " + getIncomeAmount(IIncomeDao.getAllIncome()));

            //adding a new income
            //Income inc = new Income("bake sale", 34.22, LocalDate.of(2025,1,3));
            //IIncomeDao.addNewIncome(inc);
            //checking to see any changes
            //displayAllIncome(IIncomeDao.getAllIncome());

            //deleting an existing income
            //IIncomeDao.deleteExistingIncome(3);
            //checking to see any changes
            //displayAllIncome(IIncomeDao.getAllIncome());
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //INCOME & EXPENSES
            //retrieving a list of Income objects that refer to income in the database at a certain incurred year and month
            //System.out.println("List of Income objects incurred at January of 2025: " + IIncomeDao.getListOfIncomeOfCertainMonth(2025,1));

            //retrieving a list of Expense objects that refer to expenses in the database at a certain incurred year and month
            //System.out.println("List of Expense objects incurred at January of 2025: " + IExpenseDao.getListOfExpensesOfCertainMonth(2025,1));
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //planing the application's functionality adn what methods i still need to develop to start building hte app
            // 1 to List all expenses incurred and calculate the total spend
            //      retrieve a list of all expenses _____________________________________________Method Done
            //      display the list of expenses neatly__________________________________________Method Done
            //      calculate the amount spent on all expenses in total__________________________Method unfinished
            //      display the calculation
            // 2 to Add a new expense
            //      take user input for creating a new expense and store it in multiple variables
            //      verify if the user input is valid (if not, ask for user input again)
            //      once the user input is valid, save create and expense object
            //      add the expense type object to the database__________________________________Method Done
            // 3 to Delete an expense
            //      retrieve a list of all expense ids___________________________________________Method unfinished
            //      display the list of expense ids to the user
            //      ask for teh user to pick one id
            //      display neatly an expense with the chosen id_________________________________Methods unfinished
            //      ask for user confirmation to delete the expense (if cancels, nothing happens)
            //      if user agrees, the expense with that id gets deleted from the database______Method Done
            // 4 to List all income earned and calculate total income
            //      retrieve a list of all income________________________________________________Method Done
            //      display the list of income neatly____________________________________________Method Done
            //      calculate the amount earned from all income in total_________________________Method unfinished
            //      display the calculation
            // 5 to Add a new income
            //      take user input for creating a new income and store it in multiple variables
            //      verify if the user input is valid (if not, ask for user input again)
            //      once the user input is valid, save create and income object
            //      add the income type object to the database____________________________________Method Done
            // 6 to Delete an income
            //      retrieve a list of all income ids_____________________________________________Method unfinished
            //      display the list of income ids to the user
            //      ask for teh user to pick one id
            //      display neatly an income with the chosen id___________________________________Methods unfinished
            //      ask for user confirmation to delete the income (if cancels, nothing happens)
            //      if user agrees, the income with that id gets deleted from the database________Method Done
            // 7 to List all income and expenses for a particular month and display the total income, expenditure, and how much money they should have left over.
            //      retrieve a list of income at a certain month at a certain year________________Method Done
            //      retrieve a list of expenses at a certain month at a certain year______________Method Done
            //      neatly display the income list________________________________________________Method Done
            //      neatly display the expense list_______________________________________________Method Done
            //      calculate the income earned___________________________________________________Method unfinished
            //      calculate the expenses spent__________________________________________________Method unfinished
            //      display the mount earned and spent during that specific time period
            //      display the money left at that specific time period (income - expenses)

            //methods I still need to make:
            // -> retrieve a list of all expense ids
            // -> retrieve a list of all income ids
            // -> retrieve an Expense type object based on an id
            // -> retrieve an Income type object based on an id
            // -> display neatly an expense based on its id number
            // -> display neatly an income based on its id number

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

    //------------------------------------------------------------------------------------------------------------------
    // methods for calculating money
    //------------------------------------------------------------------------------------------------------------------
    //method for calculating income
    public static double getIncomeAmount(List<Income> incomeList)
    {
        double totalAmountEarned = 0.0; //initiates the return value

        //calculates the total income
        for(Income inc : incomeList)
        {
            totalAmountEarned += inc.getAmountEarned();
        }

        return totalAmountEarned;
    }

    //method for calculating expenses
    public static double getExpensesAmount(List<Expense> expenseList)
    {
        double totalAmountSpent = 0.0; //initializes the return value

        //calculate the total amount spent
        for(Expense exp : expenseList)
        {
            totalAmountSpent += exp.getAmountSpent();
        }

        return totalAmountSpent;
    }
}