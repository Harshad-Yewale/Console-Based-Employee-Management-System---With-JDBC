package dao;

import domain.Employee;

import java.util.Scanner;

public interface EmployeeDao {
    void insertEmployee(Employee employee);
    void listEmployee();
}
