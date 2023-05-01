package com.ntu;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.ntu.domain.Expenses;
import com.ntu.dao.ExpensesDAO;
import com.ntu.dao.ExpensesDAOImpl;
import com.ntu.domain.Income;
import com.ntu.dao.IncomeDAO;
import com.ntu.dao.IncomeDAOImpl;
import com.ntu.domain.MyBudget;
import com.ntu.dao.MyBudgetDAO;
import com.ntu.dao.MyBudgetDAOImpl;
public class Main {
    public static void main(String[] args) {
        ExpensesDAO expensesDAO = new ExpensesDAOImpl();
        IncomeDAO incomeDAO = new IncomeDAOImpl();
        MyBudgetDAO myBudgetDAO=new MyBudgetDAOImpl();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("      Витрати:                           Доходи:                         Користувачі:              ");
            System.out.println("1: додати витрату                   5: додати дохід                9: додати користувача           ");
            System.out.println("2: видалити витрату                 6: видалити дохід             10: видалити користувача         ");
            System.out.println("3: список витрат                    7: список доходів             11: список користувачів          ");
            System.out.println("4: змінити дані про витрати         8: змінити дані про дохід     12: змінити дані про користувача ");

            System.out.print("-> ");

            String s = sc.nextLine();
            switch (s) {
                case "1":
                    System.out.print("Внесіть назву витрати: ");
                    String NameOfExpenses = sc.nextLine();
                    System.out.print("Внесіть дату: ");
                    String date = sc.nextLine();
                    System.out.print("Внесіть суму витрати: ");
                    double sum = sc.nextInt();
                    Date datein = DateUtil.convertStringIntoSqlDate("2020-05-23");
                    Expenses expenses = new Expenses(NameOfExpenses,datein,sum );
                    expensesDAO.insertExpenses(expenses);
                    break;
                case "2":
                    System.out.print("Внесіть ID витрати: ");
                    int expensesIdDel = sc.nextInt();
                    expensesDAO.deleteExpenses(expensesIdDel);
                    break;
                case "3":
                    List<Expenses> expenses1 = expensesDAO.getAllExpenses();
                    Iterator var39 = expenses1.iterator();

                        Expenses item = (Expenses) var39.next();
                        System.out.println(item);
                    break;
                case "4":
                    System.out.print("Внесіть id витрати: ");
                    long id = Long.parseLong(sc.nextLine());
                    System.out.print("Змініть назву витрати: ");
                    String NameOfExpenses1 = sc.nextLine();
                    System.out.print("Змініть дату: ");
                    String date1 = sc.nextLine();
                    System.out.print("Змініть суму витрати: ");
                    double sum1 = sc.nextInt();
                    Date dateIn = DateUtil.convertStringIntoSqlDate("2020-05-23");
                    Expenses expenses2 = new Expenses(id,NameOfExpenses1,dateIn,sum1 );
                    expensesDAO.updateExpenses(expenses2);
                    break;
                case "5":
                    System.out.print("Внесіть назву доходу: ");
                    String NameOfIncome = sc.nextLine();
                    System.out.print("Внесіть дату: ");
                    String date2 = sc.nextLine();
                    System.out.print("Внесіть суму: ");
                    double sum2 = sc.nextInt();
                    Date datein1 = DateUtil.convertStringIntoSqlDate("2020-05-23");
                    Income income = new Income(NameOfIncome,datein1,sum2 );
                    incomeDAO.insertIncome(income);
                    break;
                case "6":
                    System.out.print("Внесіть ID доходу: ");
                    int incomeIdDel = sc.nextInt();
                    incomeDAO.deleteIncome(incomeIdDel);
                    break;
                case "7":
                    List<Income> income1 = incomeDAO.getAllIncome();
                    Iterator var36 = income1.iterator();

                    Income item1 = (Income) var36.next();
                    System.out.println(item1);
                    break;
                case "8":
                    System.out.print("Внесіть id доходу: ");
                    long id1 = Long.parseLong(sc.nextLine());
                    System.out.print("Змініть назву доходу: ");
                    String NameOfIncome1 = sc.nextLine();
                    System.out.print("Змініть дату: ");
                    String date3 = sc.nextLine();
                    System.out.print("Змініть суму доходу: ");
                    double sum3 = sc.nextInt();
                    Date dateIn1 = DateUtil.convertStringIntoSqlDate("2020-05-23");
                    Income income2 = new Income(id1,NameOfIncome1,dateIn1,sum3 );
                    incomeDAO.updateIncome(income2);
                    break;
                case "9":
                    System.out.print("Внесіть id користувача: ");
                    long id2 = Long.parseLong(sc.nextLine());
                    System.out.print("Внесіть id доходу: ");
                    int incomeId = sc.nextInt();
                    System.out.print("Внесіть id витрати: ");
                    int expensesId = sc.nextInt();
                    System.out.print("Внесіть прізвище:\n");
                    String FirstName = sc.nextLine();
                    System.out.print("Внесіть  ім'я:\n ");
                    String MiddleName = sc.nextLine();
                    System.out.print("Внесіть по-батькові: \n");
                    String LastName = sc.nextLine();
                    Expenses expensesFromDB = expensesDAO.getExpensesById(expensesId);
                    Income incomeFromDB = incomeDAO.getIncomeById(incomeId);
                    MyBudget myBudget = new MyBudget(id2,expensesFromDB,incomeFromDB,FirstName,MiddleName,LastName);
                    myBudgetDAO.insertMyBudget(myBudget);
                    break;
                case "10":
                    System.out.print("Внесіть ID користувача: ");
                    int mybudgetdel = sc.nextInt();
                    myBudgetDAO.deleteMyBudget(mybudgetdel);
                    break;
                case "11":
                    System.out.print("Внесіть id користувача: ");
                    long id3 = Long.parseLong(sc.nextLine());
                    System.out.print("Внесіть id доходу: ");
                    int incomeId1 = sc.nextInt();
                    System.out.print("Внесіть id витрати: ");
                    int expensesId1 = sc.nextInt();
                    System.out.print("Внесіть прізвище:\n");
                    String FirstName1 = sc.nextLine();
                    System.out.print("Внесіть  ім'я:\n ");
                    String MiddleName1 = sc.nextLine();
                    System.out.print("Внесіть по-батькові: \n");
                    String LastName1 = sc.nextLine();
                    Expenses expensesFromDB1 = expensesDAO.getExpensesById(expensesId1);
                    Income incomeFromDB1 = incomeDAO.getIncomeById(incomeId1);
                    MyBudget myBudget1 = new MyBudget(id3,expensesFromDB1,incomeFromDB1,FirstName1,MiddleName1,LastName1);
                    myBudgetDAO.updateMyBudget(myBudget1);
                    break;
                case "12":
                    List<MyBudget> myBudgets =myBudgetDAO.getAllMyBudget();
                    Iterator<MyBudget> var40 = myBudgets.iterator();

                    MyBudget item3 = (MyBudget) var40.next();
                    System.out.println(item3);
                    break;

                default:
                    return;
            }
        }
    }
}
