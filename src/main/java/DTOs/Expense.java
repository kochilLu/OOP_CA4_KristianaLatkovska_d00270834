package DTOs;

import java.time.LocalDate;

public class Expense {

    int id;
    String title;
    String category;
    double amountSpent;
    LocalDate date;

    //full constructor
    public Expense(int id, String title, String category, double amountSpent, LocalDate date) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amountSpent = amountSpent;
        this.date = date;
    }

    //partial constructor
    public Expense(String title, String category, double amountSpent, LocalDate date) {
        this.id = 0;
        this.title = title;
        this.category = category;
        this.amountSpent = amountSpent;
        this.date = date;
    }

    //partial constructor
    public Expense(int id)
    {
        this.id = id;
        this.title = "";
        this.category = "";
        this.amountSpent = 0.0;
        this.date = null;
    }

    //getters
    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getCategory(){return category;}
    public double getAmountSpent(){return amountSpent;}
    public LocalDate getDate(){return date;}

    //setters
    public void setId(int id){this.id = id;}
    public void setTitle(String title){this.title = title;}
    public void setCategory(String category){this.category = category;}
    public void setAmountSpent(double amountSpent){this.amountSpent = amountSpent;}
    public void setDate(LocalDate date){this.date = date;}

    @Override
    public String toString() {
        return "Expense{id: " + id + ", title: " + title + ", category: " + category + ", amountSpent: " + amountSpent + ", date: " + date + "}";
    }
}