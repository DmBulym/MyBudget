package com.ntu.dao;

import com.ntu.domain.Income;

import java.util.Date;
import java.util.List;
public interface IncomeDAO {
    Income getIncomeById(long id);
    List<Income> getIncomeByNameOfIncomeAndDate(String NameOfExpenses, Date date);
    List<Income> getAllIncome();
    boolean insertIncome(Income income);
    boolean updateIncome(Income income);
    boolean deleteIncome(long id);
}