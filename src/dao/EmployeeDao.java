package dao;

import domain.Employee;
import util.SearchOptions;

import java.util.List;

public interface EmployeeDao {
    void insertEmployee(Employee employee);
    List<Employee> listEmployee();
    void deleteEmployee(int id);
    List<Employee> searchEmployee(SearchOptions searchOption,Object value);
    void updateEmployee(Employee employee);

}
