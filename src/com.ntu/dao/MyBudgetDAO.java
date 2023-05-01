package com.ntu.dao;

import com.ntu.domain.MyBudget;

import java.util.List;

public interface MyBudgetDAO {
        MyBudget getMyBudgetById(long id);
        List<MyBudget> getMyBudgetBySurnameNameAndlastName(String Surname, String LastName);
        List<MyBudget> getAllMyBudget();
        boolean insertMyBudget(MyBudget myBudget);
        boolean updateMyBudget(MyBudget myBudget);
        boolean deleteMyBudget(long id);

}
