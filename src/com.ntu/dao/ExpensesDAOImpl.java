package com.ntu.dao;
import com.ntu.ConnectionFactory;
import com.ntu.domain.Expenses;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesDAOImpl implements ExpensesDAO{
    @Override
    public Expenses getExpensesById(long id) {
        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Expenses WHERE id=" + id);
        )
        {
            if(rs.next())
            {

                Expenses expenses = new Expenses();

                expenses.setId(rs.getLong("id"));
                expenses.setNameOfExpenses( rs.getString("NameOfExpenses") );
                expenses.setDate( rs.getDate("Date") );
                expenses.setSum( rs.getDouble("Sum") );
                return expenses;


            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Expenses> getExpensesByNameOfExpensesAndDate(String NameOfExpenses, Date date) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM Expenses WHERE NameOfExpenses=? AND date=?"))
        {

            ps.setString(1, NameOfExpenses);
            ps.setDate(2, (java.sql.Date) date);

            ResultSet rs = ps.executeQuery();

            List<Expenses> expenses1 = new ArrayList<>(); //змінна для формування списку витрат

            while(rs.next())
            {
                Expenses expenses = extractExpensesFromResultSet(rs);
                expenses1.add(expenses);  //додаємо одну витрату до списку
            }

            return expenses1; //повертаємо список витрат

        } catch (SQLException ex) {
            ex.printStackTrace();

        }


        return null;
    }

    @Override
    public List<Expenses> getAllExpenses() {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Expenses");
        )
        {

            List<Expenses> expenses1 = new ArrayList<>(); //змінна для формування списку витрат

            while(rs.next())
            {
                Expenses expenses = extractExpensesFromResultSet(rs);
                expenses1.add(expenses);  //додаємо одну витрату до списку
            }

            return expenses1; //повертаємо список витрат

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public boolean insertExpenses(Expenses expenses) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Expenses(NameOfExpenses, date, sum ) VALUES (?, ?, ?)");
        )
        {

            ps.setString(1, expenses.getNameOfExpenses());
            ps.setDate(2, expenses.getDate());
            ps.setInt(3, (int) expenses.getSum());

            int i = ps.executeUpdate(); // for INSERT, UPDATE & DELETE

            if(i == 1) {

                return true;

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean updateExpenses(Expenses expenses) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE Expenses set NameOfExpenses = ?, date = ?, sum = ? WHERE id=?");
        )
        {
            ps.setString(1, expenses.getNameOfExpenses());
            ps.setDate(2, expenses.getDate());
            ps.setInt(3, (int) expenses.getSum());
            ps.setLong(4, expenses.getId());
            int i = ps.executeUpdate(); // for INSERT, UPDATE & DELETE

            if(i == 1) {

                return true;

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean deleteExpenses(long id) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM Expenses WHERE id=?");
        )
        {

            ps.setLong(1, id);

            int i = ps.executeUpdate(); // for INSERT, UPDATE & DELETE

            if(i == 1) {

                return true;

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return false;
    }

    private Expenses extractExpensesFromResultSet(ResultSet rs) throws SQLException {
        Expenses expenses = new Expenses();
        expenses.setId(rs.getLong("id"));
        expenses.setNameOfExpenses(rs.getString("NameOfExpenses"));
        expenses.setDate(rs.getDate("date"));
        expenses.setSum(rs.getDouble("Sum"));
        return expenses;
    }
}