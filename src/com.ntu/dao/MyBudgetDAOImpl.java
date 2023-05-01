package com.ntu.dao;
        import com.ntu.ConnectionFactory;
        import com.ntu.domain.Expenses;
        import com.ntu.domain.Income;
        import com.ntu.domain.MyBudget;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class MyBudgetDAOImpl implements MyBudgetDAO {
    @Override
    public MyBudget getMyBudgetById(long id) {
        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM MyBudget WHERE id=" + id);
        )
        {
            if(rs.next())
            {

                MyBudget myBudget = new MyBudget();
                myBudget.setId(rs.getLong("id"));
                myBudget.setSurname( rs.getString("Surname") );
                myBudget.setMiddleName( rs.getString("MiddleName") );
                myBudget.setLastName( rs.getString("LastName") );
                return myBudget;


            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }
    @Override
    public List<MyBudget> getMyBudgetBySurnameNameAndlastName(String Surname, String lastName) {
       //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM MyBudget WHERE firstName=? AND lastName=?"))
        {

            ps.setString(1, Surname);
            ps.setString(2, lastName);


            ResultSet rs = ps.executeQuery();

            List<MyBudget> myBudgets = new ArrayList<>(); //змінна для формування списку користувачів

            while(rs.next())
            {
                MyBudget myBudget = extractMyBudgetFromResultSet(rs);
                myBudgets.add(myBudget);  //додаємо одного  користувача до списку
            }

            return myBudgets; //повертаємо список витрат

        } catch (SQLException ex) {
            ex.printStackTrace();

        }


        return null;

    }

    @Override
    public List<MyBudget> getAllMyBudget() {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM mybudget");
        )
        {

            List<MyBudget> myBudgets = new ArrayList<>(); //змінна для формування списку

            while(rs.next())
            {
                MyBudget myBudget = extractMyBudgetFromResultSet(rs);
                myBudgets.add(myBudget);  //додаємо
            }

            return myBudgets; //повертаємо список

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return null;
        }

    @Override
    public boolean insertMyBudget(MyBudget myBudget) {
        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO MyBudget(IncomeId, ExpensesId, firstName, MiddleName,LastName) VALUES ( ?, ?, ?, ?, ?)");

        )
        {
            ps.setLong(1, myBudget.getIncome().getId());
            ps.setLong(2, myBudget.getExpenses().getId());
            ps.setString(3,myBudget.getSurname());
            ps.setString(4,myBudget.getMiddleName());
            ps.setString(5,myBudget.getLastName());

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
    public boolean updateMyBudget(MyBudget myBudget) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE mybudget set firstName = ?,middleName = ?,lastName= ?, IncomeId = ?, ExpensesId = ? WHERE id=?");
        )
        {
            ps.setString(1, myBudget.getSurname());
            ps.setString(2, myBudget.getMiddleName());
            ps.setString(3, myBudget.getLastName());
            ps.setLong(4, myBudget.getIncome().getId());
            ps.setLong(5, myBudget.getExpenses().getId());
            ps.setLong(6, myBudget.getId());
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
    public boolean deleteMyBudget(long id) {

        //try-with-resources
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM MyBudget WHERE id=?");
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

    private MyBudget extractMyBudgetFromResultSet(ResultSet rs) throws SQLException {
        IncomeDAO incomeDAO =  new IncomeDAOImpl();
        ExpensesDAO expensesDAO =  new ExpensesDAOImpl();

        Expenses expenses = expensesDAO.getExpensesById(rs.getLong("ExpensesId"));
        Income income = incomeDAO.getIncomeById(rs.getLong("IncomeId"));
        MyBudget myBudget = new MyBudget();
        myBudget.setId(rs.getLong("id"));
        myBudget.setExpenses( expenses );
        myBudget.setIncome( income );
        myBudget.setSurname(rs.getString("Surname"));
        myBudget.setMiddleName(rs.getString("MiddleName"));
        myBudget.setLastName(rs.getString("LastName"));
        return myBudget;
    }
}