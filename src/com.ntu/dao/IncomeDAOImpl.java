package com.ntu.dao;
import com.ntu.ConnectionFactory;
import com.ntu.domain.Expenses;
import com.ntu.domain.Income;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class IncomeDAOImpl implements IncomeDAO{

    @Override
    public Income getIncomeById(long id) {
        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Income WHERE id=" + id);
        )
        {
            if(rs.next())
            {
                Income income = new Income();

                income.setId(rs.getLong("id"));
                income.setNameOfIncome( rs.getString("NameOfIncome") );
                income.setDate( rs.getDate("Date") );
                income.setSum( rs.getDouble("Sum") );
                return income;


            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Income> getIncomeByNameOfIncomeAndDate(String NameOfIncome, Date date) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM Income WHERE NameOfExpenses=? AND date=?"))
        {

            ps.setString(1, NameOfIncome);
            ps.setDate(2, (java.sql.Date) date);

            ResultSet rs = ps.executeQuery();

            List<Income> incomes = new ArrayList<>(); //змінна для формування списку доходів

            while(rs.next())
            {
                Income income = extractIncomeFromResultSet(rs);
                incomes.add(income);  //додаємо однин дохід до списку
            }

            return incomes; //повертаємо список доходів

        } catch (SQLException ex) {
            ex.printStackTrace();

        }


        return null;
    }

    @Override
    public List<Income> getAllIncome() {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Income");
        )
        {

            List<Income> incomes = new ArrayList<>(); //змінна для формування списку доходів

            while(rs.next())
            {
                Income income = extractIncomeFromResultSet(rs);
                incomes.add(income);  //додаємо однин дохід до списку
            }

            return incomes; //повертаємо список витрат

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public boolean insertIncome(Income income) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Income(NameOfIncome, date, sum ) VALUES (?, ?, ?)");
        )
        {
            ps.setString(1, income.getNameOfIncome());
            ps.setDate(2, income.getDate());
            ps.setInt(3, (int) income.getSum());
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
    public boolean updateIncome(Income income) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE Income set NameOfIncome = ?, date = ?, sum = ? WHERE id=?");
        )
        {
            ps.setString(1, income.getNameOfIncome());
            ps.setDate(2, income.getDate());
            ps.setInt(3, (int) income.getSum());
            ps.setLong(4, income.getId());

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
    public boolean deleteIncome(long id) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM Income WHERE id=?");
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

    private Income extractIncomeFromResultSet(ResultSet rs) throws SQLException {
        Income incomes = new Income();
        incomes.setId(rs.getLong("id"));
        incomes.setNameOfIncome(rs.getString("NameOfIncome"));
        incomes.setDate(rs.getDate("date"));
        incomes.setSum(rs.getDouble("Sum"));
        return incomes;
    }
}
