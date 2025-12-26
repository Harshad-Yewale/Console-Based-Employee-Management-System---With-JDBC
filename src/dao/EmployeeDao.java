package dao;

import domain.Employee;

import java.util.List;

public interface EmployeeDao {
    void insertEmployee(Employee employee);
    List<Employee> listEmployee();
    void deleteEmployee(int id);
    List<Employee> findById(int id);
    List<Employee> findByName(String name);
    List<Employee> findByPosition(String position);
    List<Employee> findBySalaryLessThen(double salary);
    List<Employee> findBySalaryGreaterThen(double salary);

}
