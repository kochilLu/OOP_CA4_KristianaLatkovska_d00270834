package BusinessObjects;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import DAOs.*;
import DTOs.Expense;
import DTOs.Income;
import Exceptions.DaoException;

public class MainApp {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in); //for user input

        //for accessing the database's data
        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();
        IncomeDaoInterface IIncomeDao = new MySqlIncomeDao();

        boolean appIsRunning = true; //for running the app

        //application
        do
        {
            //prompting and storing user input
            System.out.println("""
                    Press
                    1 to view all expenses
                    2 to add a new expense
                    3 to delete an existing expense
                    4 to view all income
                    5 to add a new income
                    6 to delete an existing income
                    7 to view income and expenses of one certain year's month
                    0 to close this application
                    """);
            int userInput = keyboard.nextInt();

            switch(userInput)
            {
                case 0:
                    System.out.println("---The application has successfully closed---");
                    appIsRunning = false; //closes the application
                    break;
                case 1:
                    viewAllExpenses(IExpenseDao);
                    break;
                case 2:
                    // take user input for creating a new expense and store it in multiple variables
                    // verify if the user input is valid (if not, ask for user input again)
                    // once the user input is valid, create and expense object
                    // add the expense type object to the database
                    break;
                case 3:
                    // retrieve a list of all expense ids
                    // display the list of expense ids to the user
                    // ask for the user to pick one id
                    // check if the user's id is valid (if not, ask them to enter a valid one)
                    // retrieve an Expense object with that chosen id
                    // display that expense object neatly
                    // ask for user confirmation to delete the expense (if cancels, nothing happens)
                    // if user agrees, the expense with that id gets deleted from the database
                    break;
                case 4:
                    viewAllIncome(IIncomeDao);
                    break;
                case 5:
                    // take user input for creating a new income and store it in multiple variables
                    // verify if the user input is valid (if not, ask for user input again)
                    // once the user input is valid, save create and income object
                    // add the income type object to the database
                    break;
                case 6:
                    // retrieve a list of all income ids
                    // display the list of income ids to the user
                    // ask for the user to pick one id
                    // check if the user's id is valid (if not, ask them to enter a valid one)
                    // retrieve an income object based on the user's given id
                    // display neatly the retrieved income
                    // ask for user confirmation to delete the income (if cancels, nothing happens)
                    // if user agrees, the income with that id gets deleted from the database
                    break;
                case 7:
                    // retrieve a list of income at a certain month at a certain year
                    // retrieve a list of expenses at a certain month at a certain year
                    // neatly display the income list
                    // neatly display the expense list
                    // calculate the income earned
                    // calculate the expenses spent
                    // display the mount earned and spent during that specific time period
                    // display the money left at that specific time period (income - expenses)
                    break;
            }
        }while(appIsRunning);

    }

    //------------------------------------------------------------------------------------------------------------------
    // methods for running the app's main intended functions:
    //     - view all expenses               - view all income
    //     - add a new expense               - add a new income
    //     - delete an existing expense      - delete an existing income
    //     - view income and expenses of one certain year's month
    //------------------------------------------------------------------------------------------------------------------
    public static void viewAllExpenses(ExpenseDaoInterface expenseDao)
    {
        try
        {
            // retrieves and stores a list of all expenses on the database
            List<Expense> listOfAllExpenses = expenseDao.getAllExpenses();
            // display the list of expenses neatly for the user
            displayAllExpenses(listOfAllExpenses);
            // displays the amount spent on all expenses in total
            System.out.printf("Total amount spent on expenses: %.2f euro\n\n", getExpensesAmount(listOfAllExpenses));
        }
        catch( DaoException e ) {
            e.printStackTrace();
        }
    }

    public static void viewAllIncome(IncomeDaoInterface incomeDao)
    {
        try
        {
            // retrieves and stores a list of all income on the database
            List<Income> listOfAllIncome = incomeDao.getAllIncome();
            // display the list of income neatly for the user
            displayAllIncome(listOfAllIncome);
            // displays the amount earned from all income in total
            System.out.printf("Total amount earned from income: %.2f euro\n\n", getIncomeAmount(listOfAllIncome));
        }
        catch( DaoException e ){
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // methods for displaying information neatly
    //------------------------------------------------------------------------------------------------------------------
    public static void displayAllExpenses(List<Expense> expenseList)
    {
        System.out.println("\nEXPENSES:");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Id\tTitle\t\t\t\t\tCategory\t\tAmount Spent\tDate");
        System.out.println("-----------------------------------------------------------------------");

        for(Expense exp : expenseList) //goes through the list of Expense type objects
        {
            System.out.printf("%d\t%-20s\t%-15s\t%-12.2f\t%s\n", exp.getId(), exp.getTitle(), exp.getCategory(), exp.getAmountSpent(), exp.getDate());
        }

        System.out.println("-----------------------------------------------------------------------");
    }

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

    public static void displayOneExpense(Expense expense)
    {
        System.out.println("\nEXPENSE with the ID " + expense.getId() + ":");
        System.out.println("-----------------------------------------");
        System.out.printf("Title: %s\nCategory: %s\nAmount Spent: %.2f euro\nDate: %s\n", expense.getTitle(), expense.getCategory(), expense.getAmountSpent(), expense.getDate());
        System.out.println("-----------------------------------------");
    }

    public static void displayOneIncome(Income income)
    {
        System.out.println("\nINCOME with the ID " + income.getId() + ":");
        System.out.println("-----------------------------------------");
        System.out.printf("Title: %s\nAmount Spent: %.2f euro\nDate: %s\n", income.getTitle(), income.getAmountEarned(), income.getDate());
        System.out.println("-----------------------------------------");
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