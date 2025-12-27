package dao;


import domain.Employee;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDaoImpl implements EmployeeDao {


    //Queries to Interact with database
    private static final String insertQuery = "Insert Into Employee (EmployeeName,EmployeePosition,EmployeeSalary) Values(?,?,?)";
    private static final String listQuery = "Select * from Employee";
    private static final String DeleteQuery = "Delete From Employee Where EmployeeID=?";
    private static final String updateQuery ="Update Employee Set EmployeeName=?,EmployeePosition=?,EmployeeSalary=? Where EmployeeId=?";

    //Insert Function
    @Override
    public void insertEmployee(Employee employee) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setString(2, employee.getEmployeePosition());
            preparedStatement.setDouble(3, employee.getEmployeeSalary());

            preparedStatement.executeUpdate();
            var rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int ID = rs.getInt(1);
                System.out.println("Data inserted Successfully\nYour EmployeeId is : " + ID);
            }


        } catch (SQLException e) {
            System.out.println("Error While Inserting Employee : " + e.getMessage());
        }
    }

    //listAllFunction
    @Override
    public List<Employee> listEmployee() {

        List<Employee> employeeList = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement listStatement = con.prepareStatement(listQuery);) {

            ResultSet resultSet = listStatement.executeQuery();

            while (resultSet.next()) {
                Employee e = mapEmployee(resultSet);
                employeeList.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error Occurred while listing Employees :" + e.getMessage());
        }
        return employeeList;
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement deleteStatement = con.prepareStatement(DeleteQuery);
        ) {

            deleteStatement.setInt(1, id);
            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee Removed from the Database");
            } else {
                System.out.println("Employee with Id: " + id + " does not exist");
            }

        } catch (SQLException e) {
            System.out.println("Error occurred while Deleting Employee " + e.getMessage());
        }
    }


    @Override
    public List<Employee> findById(int id) {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapEmployee(rs));
            }

        } catch (SQLException e) {
            System.out.println("Search by ID failed: " + e.getMessage());
        }

        return list;
    }


    @Override
    public List<Employee> findByName(String name) {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE EmployeeName Like ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapEmployee(rs));
            }

        } catch (SQLException e) {
            System.out.println("Search by name failed: " + e.getMessage());
        }

        return list;
    }


    @Override
    public List<Employee> findByPosition(String position) {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE EmployeePosition = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, position);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapEmployee(rs));
            }

        } catch (SQLException e) {
            System.out.println("Search by position failed: " + e.getMessage());
        }

        return list;
    }


    @Override
    public List<Employee> findBySalaryLessThen(double salary) {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE EmployeeSalary <= ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, salary);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapEmployee(rs));
            }

        } catch (SQLException e) {
            System.out.println("Search by salary failed: " + e.getMessage());
        }

        return list;
    }
@Override
    public List<Employee> findBySalaryGreaterThen(double salary) {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE EmployeeSalary >= ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, salary);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapEmployee(rs));
            }

        } catch (SQLException e) {
            System.out.println("Search by salary failed: " + e.getMessage());
        }

        return list;
    }

    @Override
    public void updateEmployee(Employee employee) {

        try(Connection con=DBConnection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(updateQuery)){

            preparedStatement.setString(1,employee.getEmployeeName());
            preparedStatement.setString(2,employee.getEmployeePosition());
            preparedStatement.setDouble(3,employee.getEmployeeSalary());
            preparedStatement.setInt(4,employee.getEmployeeId());

            int result=preparedStatement.executeUpdate();
            if(result>0){
                System.out.println("Employee With EmployeeId: "+employee.getEmployeeId()+" Updated Successfully");
            }
            else{
                System.out.println("Employee Does not Exist");
            }

        }
        catch (SQLException e){
            System.out.println("Error Occurred While Updating Employee: "+e.getMessage());
        }

    }

    private Employee mapEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("EmployeeID"),
                rs.getString("EmployeeName"),
                rs.getString("EmployeePosition"),
                rs.getDouble("EmployeeSalary")
        );
    }
}



