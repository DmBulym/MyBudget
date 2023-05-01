package com.ntu.domain;

import java.io.Serializable;
public class MyBudget implements Serializable {
    private static final long serialVersionUID = 1L;
    //поля
    private long id;
    //назви полів співпадають з назвами колонок таблиці personReader в dbntu
    private Expenses expensesId;
    private String Surname;
    private String MiddleName;
    private String LastName;
    private Income incomeId;
    //конструктор 1
    public MyBudget() {
        super();
    }
    //конструктор 2
    public MyBudget(Expenses expensesId, String Surname, Income incomeId, String MiddleName,String LastName) {
        super();
        this.expensesId = expensesId;
        this.Surname = Surname;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.incomeId = incomeId;
    }
    //конструктор 3
    public MyBudget(long id, Expenses expensesId,Income incomeId, String Surname, String MiddleName, String LastName) {
        super();
        this.id = id;
        this.expensesId = expensesId;
        this.incomeId = incomeId;
        this.Surname = Surname;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
    }

    //конструктор 4
    public MyBudget(Expenses expensesId,Income incomeId, String Surname, String MiddleName, String LastName) {
        super();
        this.expensesId = expensesId;
        this.Surname = Surname;
        this.MiddleName = MiddleName;
        this.incomeId = incomeId;
        this.LastName = LastName;

    }

    public Income getIncome() {
        return incomeId;
    }

    public void setIncome(Income incomeId) {
        this.incomeId = incomeId;
    }
    public Expenses getExpenses() {
        return expensesId;
    }

    public void setExpenses(Expenses expensesId) {
        this.expensesId = expensesId;
    }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getSurname(){
        return Surname;
    }
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    public String getMiddleName(){
        return MiddleName;
    }
    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }
    public String getLastName(){
        return LastName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    @Override
    public String toString() {
        return "Expenses [id=" + id + ", Expenses=" + expensesId.getId() + ", Income=" + incomeId.getId() + ", FirstName="
                + Surname + ", MiddleName=" + MiddleName+ ", LastName=" + LastName+ "]";
    }
}
