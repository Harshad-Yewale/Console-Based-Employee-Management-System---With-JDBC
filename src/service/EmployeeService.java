package service;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import domain.Employee;
import util.SearchOptions;
import util.SortOptions;

import java.util.*;

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

    public void employeeListMethod(SortOptions option) {
        List<Employee> employeeList = new ArrayList<>(employeeDao.listEmployee());
        Comparator<Employee> comparator=sortStrategy.get(option);

        if(comparator!=null){
            employeeList.sort(comparator);
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
    private  static final Map<SortOptions,Comparator<Employee>> sortStrategy=
            Map.of(
                    SortOptions.sortBySalary,
                    Comparator.comparingDouble(Employee::getEmployeeSalary),

                    SortOptions.sortBySalaryDESC,
                    Comparator.comparingDouble(Employee::getEmployeeSalary).reversed(),

                    SortOptions.sortByName,
                    Comparator.comparing(Employee::getEmployeeName)
            );

    public void removeEmployee(int id) {
        if (id <= 0) {
            System.out.println("Invalid Employee Id Please Enter a Valid EmployeeId");
            return;
        }
        employeeDao.deleteEmployee(id);
    }

    public void employeeSearchMethod(SearchOptions searchChoice, Scanner sc){
        List<Employee> employeeList = new ArrayList<>();
        Object value;

        switch (searchChoice){
            case BY_ID -> {
                System.out.println("Enter Employee Id:");
                value=Integer.parseInt(sc.nextLine());
            }
            case BY_NAME -> {
                System.out.println("Enter Employee Name:");
                value=sc.nextLine();
            }
            case BY_POSITION-> {
                System.out.println("Enter Employee Position:");
                value=sc.nextLine();
            }
            case BY_SALARY_LESS_THAN,BY_SALARY_GREATER_THAN -> {
                System.out.println("Enter Employee Salary:");
                value=Double.parseDouble(sc.nextLine());
            }
            default -> {
                System.out.println("Invalid Option");
                return;
            }

        }
        employeeList=employeeDao.searchEmployee(searchChoice,value);

        printEmployees(employeeList);
    }

    public void updateEmp(int id,Scanner sc){
        List<Employee> updateList=new ArrayList<>();
        if(id<=0){
            System.out.println("Invalid Id Enter a Valid Id : ");
            return;
        }
        updateList=employeeDao.searchEmployee(SearchOptions.BY_ID,id);
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
