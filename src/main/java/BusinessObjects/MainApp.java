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
                    deleteExistingExpense(IExpenseDao);
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
                    deleteExistingIncome(IIncomeDao);
                    break;
                case 7:
                /*    //initiating values
                    boolean validUserInput = false;
                    int year = 0;
                    int month = 0;

                    //getting user input
                    while(!validUserInput)
                    {
                        System.out.println("Enter a year: ");
                        year = keyboard.nextInt();
                        System.out.println("Enter a month: ");
                        month = keyboard.nextInt();

                        //validating user input
                        //if(validYear(year) && validMonth(month))
                        //{
                        //    validUserInput = true;
                        //}
                    }

                    //displaying information about income and expenses based on the user's chosen month and year
                    viewExpensesAndIncomeOfParticularMonthOfYear(year, month, IIncomeDao, IExpenseDao);
                */
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

    public static void viewExpensesAndIncomeOfParticularMonthOfYear(int year, int month, IncomeDaoInterface incomeDao, ExpenseDaoInterface expenseDao)
    {
        try
        {
            // retrieving and storing a list of income & expenses at a certain month of a certain year
            List<Income> listOfIncomeOfParticularMonthOfYear = incomeDao.getListOfIncomeOfCertainMonth(year, month);
            List<Expense> listOfExpensesOfParticularMonthOfYear = expenseDao.getListOfExpensesOfCertainMonth(year, month);

            // neatly display the income & expense list
            displayAllExpenses(listOfExpensesOfParticularMonthOfYear);
            displayAllIncome(listOfIncomeOfParticularMonthOfYear);

            // display the income earned & expenses spent
            System.out.printf("Money earned this month from income: %.2f euro\n", getIncomeAmount(listOfIncomeOfParticularMonthOfYear));
            System.out.printf("Money spent this month for expenses: %.2f euro\n", getExpensesAmount(listOfExpensesOfParticularMonthOfYear));

            // display the total money left at that specific time period
            System.out.printf("Total Money left at the end of the month (considering only these expenses and income): %.2f euro\n\n", getIncomeAmount(listOfIncomeOfParticularMonthOfYear)-getExpensesAmount(listOfExpensesOfParticularMonthOfYear));
        }
        catch( DaoException e ){
            e.printStackTrace();
        }
    }

    public static void deleteExistingExpense(ExpenseDaoInterface expenseDao)
    {
        Scanner keyboard = new Scanner(System.in); //for user input

        try
        {
            // retrieves and stores a list of all expense ids
            List<Integer> expenseIds = expenseDao.getListOfAllExpenseIds();

            boolean validUserInput = false; //for controlling that the user enters a valid id
            boolean validUserConfirmation = false; //for controlling that the user gives a valid confirmation of action
            int id = 0; //initiating the value of user's input

            while(!validUserInput)
            {
                //prompt's the user to choose an id
                System.out.println("Pick the ID of the expense you want to delete:" + expenseIds);
                id = keyboard.nextInt();

                //check if the user's id is valid (if not, ask them to enter a valid one)
                if(expenseIds.contains(id)) {
                    validUserInput = true;
                }
                else{
                    System.out.println("Invalid input. Please pick one of the offered IDs");
                }
            }

            // displaying an Expense object with the users chosen id
            displayOneExpense(expenseDao.getExpenseById(id));

            // ask for user confirmation to delete the expense (if cancels, nothing happens)
            while(!validUserConfirmation)
            {
                //prompting user input
                System.out.println("\nAre you sure you want to delete this expense?\nPress 1 for yes or 0 for no");
                int userConfirmation = keyboard.nextInt();

                //evaluating user input
                if(userConfirmation == 1) //user agrees to delete expense
                {
                    System.out.println("\n---The expense with the id " + id + " has been deleted---\n");
                    expenseDao.deleteExistingExpense(id); //deletes the expense from the database
                    validUserConfirmation = true; //ends while loop, returns to the main menu
                }
                else if(userConfirmation == 0) //user cancels expense deletion
                {
                    System.out.println("\n---The deletion of expense with the id " + id + " has been canceled---\n");
                    validUserConfirmation = true; //ends while loop, returns to the main menu
                }
                else{ //user gives invalid input
                    System.out.println("Input error! Please pick one of the given answers.");
                }
            }
        }
        catch( DaoException e ){
            e.printStackTrace();
        }
    }

    public static void deleteExistingIncome(IncomeDaoInterface incomeDao)
    {
        Scanner keyboard = new Scanner(System.in); //for user input

        try
        {
            // retrieves and stores a list of all expense ids
            List<Integer> incomeIds = incomeDao.getListOfAllIncomeIds();

            boolean validUserInput = false; //for controlling that the user enters a valid id
            boolean validUserConfirmation = false; //for controlling that the user gives a valid confirmation of action
            int id = 0; //initiating the value of user's input

            while(!validUserInput)
            {
                //prompt's the user to choose an id
                System.out.println("Pick the ID of the income you want to delete:" + incomeIds);
                id = keyboard.nextInt();

                //check if the user's id is valid (if not, ask them to enter a valid one)
                if(incomeIds.contains(id)) {
                    validUserInput = true;
                }
                else{
                    System.out.println("Invalid input. Please pick one of the offered IDs");
                }
            }

            // displaying an Income object with the users chosen id
            displayOneIncome(incomeDao.getIncomeById(id));

            // ask for user confirmation to delete the income (if cancels, nothing happens)
            while(!validUserConfirmation)
            {
                //prompting user input
                System.out.println("\nAre you sure you want to delete this income?\nPress 1 for yes or 0 for no");
                int userConfirmation = keyboard.nextInt();

                //evaluating user input
                if(userConfirmation == 1) //user agrees to delete income
                {
                    System.out.println("\n---The income with the id " + id + " has been deleted---\n");
                    incomeDao.deleteExistingIncome(id); //deletes the income from the database
                    validUserConfirmation = true; //ends while loop, returns to the main menu
                }
                else if(userConfirmation == 0) //user cancels income deletion
                {
                    System.out.println("\n---The deletion of income with the id " + id + " has been canceled---\n");
                    validUserConfirmation = true; //ends while loop, returns to the main menu
                }
                else{ //user gives invalid input
                    System.out.println("Input error! Please pick one of the given answers.");
                }
            }
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