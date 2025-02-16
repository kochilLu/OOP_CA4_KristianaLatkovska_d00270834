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

    @Override
    public boolean equals(Object obj)
    {
        //is equal to this object if it is this object
        if (obj == this) {
            return true;
        }
        //is not equal to this object if it is not a YearMonth type object
        if (! (obj instanceof YearMonth)) {
            return false;
        }

        //down-casting the object to be able to compare its values with this YearMonth object
        YearMonth other = (YearMonth)obj;
        //is equal to this object if the other object's year and month values are the same as this object's year and month values
        return (this.year == other.year && this.month == other.month);
    }
}
