package DTOs;

import java.time.LocalDate;

public class Income {

    int id;
    String title;
    String category;
    double amountEarned;
    LocalDate date;

    //full constructor
    public Income(int id, String title, String category, double amountEarned, LocalDate date) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amountEarned = amountEarned;
        this.date = date;
    }

    //partial constructor
    public Income(String title, String category, double amountEarned, LocalDate date) {
        this.id = 0;
        this.title = title;
        this.category = category;
        this.amountEarned = amountEarned;
        this.date = date;
    }

    //getters
    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getCategory(){return category;}
    public double getAmountEarned(){return amountEarned;}
    public LocalDate getDate(){return date;}

    //setters
    public void setId(int id){this.id = id;}
    public void setTitle(String title){this.title = title;}
    public void setCategory(String category){this.category = category;}
    public void setAmountEarned(double amountEarned) {this.amountEarned = amountEarned;}
    public void setDate(LocalDate date){this.date = date;}

    @Override
    public String toString() {
        return "Income{id: " + id + ", title: " + title + ", category: " + category + ", amountEarned: " + amountEarned + ", date: " + date + "}";
    }
}
