package com.ntu.dao;
import java.util.Date;
import java.util.List;
import com.ntu.domain.Expenses;
public interface ExpensesDAO {
    Expenses getExpensesById(long id);
    List<Expenses> getExpensesByNameOfExpensesAndDate(String NameOfExpenses, Date date);
    List<Expenses> getAllExpenses();
    boolean insertExpenses(Expenses expenses);
    boolean updateExpenses(Expenses expenses);
    boolean deleteExpenses(long id);
}
