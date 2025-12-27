package service;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import domain.Employee;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    public void addEmployee(String name, String position, double salary) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }
        if (position == null || position.trim().isEmpty()) {
            System.out.println("Job Position can not be Empty");
            return;
        }
        if (salary <= 0) {
            System.out.println("Salary is Invalid");
            return;
        }

        Employee employee = new Employee(name, position, salary);
        employeeDao.insertEmployee(employee);
    }

    public void employeeListMethod(int choice) {
        List<Employee> employeeList = new ArrayList<>(employeeDao.listEmployee());
        switch (choice) {
            case 1 -> {
                // No sorting Needed
            }
            case 2 -> listEmployeeBySalary(employeeList);
            case 3 -> listEmployeeBySalaryDESC(employeeList);
            case 4 -> listEmployeeByName(employeeList);
            default -> System.out.println("Invalid sorting option");
        }
        printEmployees(employeeList);
    }

    //printing list of employees
    private void printEmployees(List<Employee> employeeList) {
        System.out.println(
                "+------------+----------------------+----------------------+-----------------+");
        System.out.printf(
                "| %-10s | %-20s | %-20s | %-15s |\n",
                "EmployeeId", "EmployeeName", "EmployeePosition", "EmployeeSalary");
        System.out.println(
                "+------------+----------------------+----------------------+-----------------+");
        if (employeeList.isEmpty()) {
            System.out.println("No data found");
            return;
        }
        for (Employee e : employeeList) {
            System.out.printf(
                    "| %-10d | %-20s | %-20s | %-15.2f |\n",
                    e.getEmployeeId(),
                    e.getEmployeeName(),
                    e.getEmployeePosition(),
                    e.getEmployeeSalary()
            );
        }
        System.out.println(
                "+------------+----------------------+----------------------+-----------------+");
    }

    //different sorting techniques
    public void listEmployeeBySalary(List<Employee> employeeList) {
        employeeList.sort(Comparator.comparingDouble(Employee::getEmployeeSalary));
    }

    public void listEmployeeBySalaryDESC(List<Employee> employeeList) {
        employeeList.sort(Comparator.comparingDouble(Employee::getEmployeeSalary).reversed());
    }

    public void listEmployeeByName(List<Employee> employeeList) {
        employeeList.sort(Comparator.comparing(Employee::getEmployeeName));
    }

    public void removeEmployee(int id) {
        if (id <= 0) {
            System.out.println("Invalid Employee Id Please Enter a Valid EmployeeId");
            return;
        }
        employeeDao.deleteEmployee(id);
    }

    public void employeeSearchMethod(int searchChoice, Scanner sc){
        List<Employee> employeeList = new ArrayList<>();

        switch (searchChoice){
            case 1->employeeList=searchEmployeeById(employeeList,sc);
            case 2->employeeList=searchEmployeeByName(employeeList,sc);
            case 3->employeeList=searchEmployeeByPosition(employeeList,sc);
            case 4->employeeList=searchEmployeeBySalaryLessThen(employeeList,sc);
            case 5->employeeList=searchEmployeeBySalaryGreaterThen(employeeList,sc);
            default -> System.out.println("Invalid options");
        }
        printEmployees(employeeList);
    }

    private List<Employee> searchEmployeeById(List<Employee> employeeList,Scanner sc) {
        System.out.print("Enter the employee Id : ");
        int id=Integer.parseInt(sc.nextLine());
        employeeList=employeeDao.findById(id);
        return employeeList;
    }
    private List<Employee> searchEmployeeByName(List<Employee> employeeList,Scanner sc) {
        System.out.print("Enter the employee Name : ");
        String name=sc.nextLine();
        employeeList=employeeDao.findByName(name);
        return employeeList;
    }

    private List<Employee> searchEmployeeByPosition(List<Employee> employeeList,Scanner sc) {
        System.out.print("Enter the employee Position : ");
        String Position=sc.nextLine();
        employeeList=employeeDao.findByPosition(Position);
        return employeeList;
    }

    private List<Employee> searchEmployeeBySalaryLessThen(List<Employee> employeeList,Scanner sc) {
        System.out.print("Enter the employee Salary : ");
        double Salary =Double.parseDouble(sc.nextLine());
        employeeList=employeeDao.findBySalaryLessThen(Salary);
        return employeeList;
    }
    private List<Employee> searchEmployeeBySalaryGreaterThen(List<Employee> employeeList,Scanner sc) {
        System.out.print("Enter the employee Salary : ");
        double Salary =Double.parseDouble(sc.nextLine());
        employeeList=employeeDao.findBySalaryGreaterThen(Salary);
        return employeeList;
    }

    public void updateEmp(int id,Scanner sc){
        List<Employee> updateList=new ArrayList<>();
        if(id<=0){
            System.out.println("Invalid Id Enter a Valid Id : ");
            return;
        }
        updateList=employeeDao.findById(id);
        printEmployees(updateList);
        if(updateList.isEmpty()) {
            System.out.println("\nEmployee Not Found ");
            return;
        }
            System.out.print("Enter the Updated Name: ");
            String name=sc.nextLine();
            System.out.print("Enter the Updated Position: ");
            String position=sc.nextLine();
            System.out.print("Enter the Updated Salary");
            double salary=Double.parseDouble(sc.nextLine());

            if(name==null || name.trim().isEmpty()){
                System.out.println("Employee Name can not be null Try Again");
                return;
            }
            if(position==null || position.trim().isEmpty()){
                System.out.println("Employee Position can not be null Try Again");
                return;
            }
            if(salary<=0){
                System.out.println("Employee Salary is Invalid Try Again");
                return;
            }
            Employee e=new Employee(id,name,position,salary);
            employeeDao.updateEmployee(e);


    }


}
