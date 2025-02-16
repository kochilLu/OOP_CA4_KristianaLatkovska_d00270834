package DTOs;

//for storing a combination of a year and a month.
//this in the main app is used to validate specific user input and make it easier for the user to interact with the main application
public class YearMonth {

    int year;
    int month;

    //full constructor
    public YearMonth(int year, int month)
    {
        this.year = year;
        this.month = month;
    }

    //getters
    public int getYear(){return year;}
    public int getMonth(){return month;}

    //setters
    public void setYear(int year){this.year = year;}
    public void setMonth(int month){this.month = month;}

    @Override
    public String toString() {
        return "YearMonth{Year: " + year + ", Month: " + month + "}";
    }
}
