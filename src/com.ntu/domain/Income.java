package com.ntu.domain;

import java.sql.Date;

public class Income   { // Дохід
    private long id;
    private String NameOfIncome; // Назва доходу
    private double sum; // Сума
    private Date date; //yyyy-mm-dd
    //конструктор 1
    public Income() {
        super();
    }
    //конструктор 2
    public Income(String NameOfIncome, Date date, double sum) {
        super();
        this.NameOfIncome = NameOfIncome;
        this.date = date;
        this.sum=sum;
    }
    //конструктор 3
    public Income(long id,String NameOfIncome, Date date,double sum) {
        super();
        this.id = id;
        this.NameOfIncome = NameOfIncome;
        this.date = date;
        this.sum=sum;

    }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getNameOfIncome(){
        return NameOfIncome;
    }
    public void setNameOfIncome(String NameOfIncome) {
        this.NameOfIncome = NameOfIncome;
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
        return "Expenses [id=" + id + ", NameOfIncome=" + NameOfIncome + ", Date=" + date + ", Sum="
                + sum + "]";
    }
}
