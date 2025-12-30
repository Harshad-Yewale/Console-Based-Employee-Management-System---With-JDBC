
package main;
import service.EmployeeService;
import util.SearchOptions;
import util.SortOptions;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        boolean exit = true;
        String choice;

        Scanner sc = new Scanner(System.in);
        EmployeeService employeeService = new EmployeeService();

        System.out.println("--------------------------------");
        System.out.println("* Employee Management System  *");
        System.out.println("--------------------------------");

        while (exit) {
            System.out.println("""
                    \n
                    Choose an option :
                    1)Add Employee
                    2)List Employees
                    3)Remove Employee
                    4)Search Employee
                    5)Update Employee
                    6)Exit
                    ---------------------------------""");
            System.out.print("Your Choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1" -> addEmployee(sc, employeeService);
                case "2" -> {
                    System.out.println("""
                            List Employees:
                            1)Normal
                            2)Sort By salary(asc)
                            3)Sort By Salary(desc)
                            4)Sort By Name""");
                    System.out.print("Your Choice: ");
                    int sortChoice=Integer.parseInt(sc.nextLine());
                    listEmployee(employeeService,sortChoice);
                }
                case "3" -> removeEmployee(sc, employeeService);
                case "4" -> {
                    System.out.println("""
                            Search Employee By:
                            1)Id
                            2)Name
                            3)Position
                            4)Salary Less then
                            5)Salary Greater then""");
                    System.out.print("Your Choice: ");
                    int searchChoice=Integer.parseInt(sc.nextLine());

                    getEmployeeDetails(sc,employeeService,searchChoice);
                }
                case "5" -> updateEmployee(employeeService,sc);
                case "6" -> exit = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void addEmployee(Scanner sc, EmployeeService employeeService) {
        try {
            System.out.print("Employee Name : ");
            String name = sc.nextLine();
            System.out.print("Employee Position : ");
            String position = sc.nextLine();
            System.out.print("Employee Salary :");
            double salary = Double.parseDouble(sc.nextLine());
            employeeService.addEmployee(name, position, salary);
        } catch (NumberFormatException e) {
            System.out.println("Error Occurred while parsing in App" + e.getMessage());
        }
    }

    private static void listEmployee(EmployeeService employeeService,int sortChoice) {

        SortOptions sortOptions=switch (sortChoice) {
            case 1 -> SortOptions.Normal;
            case 2 -> SortOptions.sortBySalary;
            case 3 -> SortOptions.sortBySalaryDESC;
            case 4 -> SortOptions.sortByName;
            default -> SortOptions.Normal;
        };
        employeeService.employeeListMethod(sortOptions);
    }

    private static void removeEmployee(Scanner sc, EmployeeService employeeService) {

        try {
            System.out.print("Enter Employee ID You Want to Remove :");
            int Id = Integer.parseInt(sc.nextLine());
            employeeService.removeEmployee(Id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input (Enter Numbers Only)");
        }
    }

    private static void getEmployeeDetails(Scanner sc, EmployeeService employeeService, int searchChoice) {


        SearchOptions choice=switch (searchChoice){
            case 1->SearchOptions.BY_ID;
            case 2->SearchOptions.BY_NAME;
            case 3->SearchOptions.BY_POSITION;
            case 4->SearchOptions.BY_SALARY_LESS_THAN;
            case 5->SearchOptions.BY_SALARY_GREATER_THAN;
            default -> null;
        };

        if(choice!=null) {
            employeeService.employeeSearchMethod(choice, sc);
        }
    }

    private static void updateEmployee(EmployeeService employeeService, Scanner sc) {
        try {
            System.out.print("Enter Employee ID You Want to Update:");
            int id = Integer.parseInt(sc.nextLine());
            employeeService.updateEmp(id,sc);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input (Enter Numbers Only)");
        }
    }

}
