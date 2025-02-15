package DTOs;

import java.time.LocalDate;

public class Income {

    int id;
    String title;
    double amountEarned;
    LocalDate date;

    //full constructor
    public Income(int id, String title, double amountEarned, LocalDate date) {
        this.id = id;
        this.title = title;
        this.amountEarned = amountEarned;
        this.date = date;
    }

    //partial constructor
    public Income(String title, double amountEarned, LocalDate date) {
        this.id = 0;
        this.title = title;
        this.amountEarned = amountEarned;
        this.date = date;
    }

    //partial constructor
    public Income(int id)
    {
        this.id = id;
        this.title = "";
        this.amountEarned = 0.0;
        this.date = null;
    }

    //getters
    public int getId(){return id;}
    public String getTitle(){return title;}
    public double getAmountEarned(){return amountEarned;}
    public LocalDate getDate(){return date;}

    //setters
    public void setId(int id){this.id = id;}
    public void setTitle(String title){this.title = title;}
    public void setAmountEarned(double amountEarned) {this.amountEarned = amountEarned;}
    public void setDate(LocalDate date){this.date = date;}

    @Override
    public String toString() {
        return "Income{id: " + id + ", title: " + title + ", amountEarned: " + amountEarned + ", date: " + date + "}";
    }
}
