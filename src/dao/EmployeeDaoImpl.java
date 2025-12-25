package dao;


import domain.Employee;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeDaoImpl implements EmployeeDao{

    //Queries to Interact with database
    private static final String insertQuery ="Insert Into Employee (EmployeeName,EmployeePosition,EmployeeSalary) Values(?,?,?)";
    private static final String listQuery="Select * from Employee";

    //Insert Function
    @Override
    public void insertEmployee(Employee employee) {
        try(Connection con=DBConnection.getConnection();
            PreparedStatement preparedStatement=con.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1,employee.getEmployeeName());
            preparedStatement.setString(2,employee.getEmployeePosition());
            preparedStatement.setDouble(3,employee.getEmployeeSalary());

            preparedStatement.executeUpdate();
            var rs=preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int ID=rs.getInt(1);
                System.out.println("Data inserted Successfully\nYour EmployeeId is : "+ID);
            }



        }
        catch (SQLException e){
            System.out.println("Error While Inserting Employee : "+e.getMessage());
        }
    }

    //listAllFunction
    @Override
    public void listEmployee() {

        boolean hasdeta=false;

        try(Connection con=DBConnection.getConnection();
        PreparedStatement listStatement= con.prepareStatement(listQuery);) {

            ResultSet resultSet=listStatement.executeQuery();

            System.out.println(
                    "+------------+----------------------+----------------------+--------------+");
            System.out.printf(
                    "| %-10s | %-20s | %-20s | %-12s |\n",
                    "EmployeeId", "EmployeeName", "EmployeePosition", "EmployeeSalary");
            System.out.println(
                    "+------------+----------------------+----------------------+--------------+");

                while (resultSet.next()) {
                    hasdeta=true;
                    System.out.printf(
                            "| %-10d | %-20s | %-20s | %-12.2f |\n",
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    );
                }

            if(!hasdeta){
                System.out.printf("\n%45s","No data found\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
