package com.ntu.domain;
import java.io.Serializable;
import java.sql.Date;

public class Expenses  implements Serializable { //Витрати
    public static final long serialVersionUID = 1L;
    private long id;
    private String NameOfExpenses; // Назва витрати
    private double sum; // Сума
    private Date date; //yyyy-mm-dd
    //конструктор 1
    public Expenses() {
        super();
    }
    //конструктор 2
    public Expenses(String NameOfExpenses, Date date, double sum) {
        super();
        this.NameOfExpenses = NameOfExpenses;
        this.date = date;
        this.sum=sum;
    }
    //конструктор 3
    public Expenses(long id,String NameOfExpenses, Date date,double sum) {
        super();
        this.id = id;
        this.NameOfExpenses = NameOfExpenses;
        this.date = date;
        this.sum=sum;

    }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getNameOfExpenses(){
        return NameOfExpenses;
    }
    public void setNameOfExpenses(String NameOfExpenses) {
        this.NameOfExpenses = NameOfExpenses;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
    @Override
    public String toString() {
        return "Expenses [id=" + id + ", NameOfExpenses=" + NameOfExpenses + ", Date=" + date + ", Sum="
                + sum + "]";
    }
}
