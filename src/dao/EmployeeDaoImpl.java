package dao;


import domain.Employee;
import util.DBConnection;
import util.SearchOptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EmployeeDaoImpl implements EmployeeDao {


    //Queries to Interact with database
    private static final String insertQuery = "Insert Into Employee (EmployeeName,EmployeePosition,EmployeeSalary) Values(?,?,?)";
    private static final String listQuery = "Select * from Employee";
    private static final String DeleteQuery = "Delete From Employee Where EmployeeID=?";
    private static final String updateQuery ="Update Employee Set EmployeeName=?,EmployeePosition=?,EmployeeSalary=? Where EmployeeId=?";

    //search Queries Map
    private static final Map<SearchOptions,String> searchQueries=Map.of(

            SearchOptions.BY_ID,"Select * from Employee where EmployeeId=?",
            SearchOptions.BY_NAME, "SELECT * FROM Employee WHERE EmployeeName LIKE ?",
            SearchOptions.BY_POSITION, "SELECT * FROM Employee WHERE EmployeePosition = ?",
            SearchOptions.BY_SALARY_LESS_THAN, "SELECT * FROM Employee WHERE EmployeeSalary < ?",
            SearchOptions.BY_SALARY_GREATER_THAN, "SELECT * FROM Employee WHERE EmployeeSalary > ?"

    );

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
    public List<Employee> searchEmployee(SearchOptions option, Object value) {

        List<Employee>result=new ArrayList<>();
        String sql=searchQueries.get(option);

        if(sql.isEmpty()){
            return result;
        }

        try(Connection connection=DBConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql)) {
            if (option == SearchOptions.BY_NAME) {
                preparedStatement.setString(1,"%"+ value +"%");
            }
            else if (value instanceof  Integer) {
                preparedStatement.setInt(1,(int) value);
            }
            else if (value instanceof Double) {
                preparedStatement.setDouble(1,(Double) value);

            }
            else {
                preparedStatement.setString(1,value.toString());
            }

            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
               result.add(mapEmployee(resultSet));
            }

        }
        catch (SQLException e){
            System.out.println("Error Occurred While Searching Employee "+e.getMessage());
        }

        return result;
    }


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



