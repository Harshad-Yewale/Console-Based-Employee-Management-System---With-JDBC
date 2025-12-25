
package main;

import service.EmployeeService;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        boolean exit=true;
        String choice;

        Scanner sc= new Scanner(System.in);
        EmployeeService employeeService = new EmployeeService();

        System.out.println("*******************************");
        System.out.println("* Employee Management System  *");
        System.out.println("*******************************");

        while (exit){

            System.out.println("""
                    Choose an option :
                    1)Add Employee
                    2)List Employees
                    3)Exit
                    """);
            choice=sc.nextLine();

            switch (choice){
                case "1"->addEmployee(sc, employeeService);
                case "2"->listEmployee(employeeService);
                case "3"->exit=false;
                default -> System.out.println("Invalid choice");
            }


        }
    }



    private static void addEmployee(Scanner sc , EmployeeService employeeService) {
        try {


            System.out.print("Employee Name : ");
            String name = sc.nextLine();
            System.out.print("Employee Position : ");
            String position = sc.nextLine();
            System.out.print("Employee Salary :");
            double salary = Double.parseDouble(sc.nextLine());
            employeeService.addEmployee(name, position, salary);
        }
        catch (InputMismatchException e){
            System.out.println("Error Occurred while taking input in App :"+e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println("Error Occurred while parsing in App"+e.getMessage());
        }
    }
    private static void listEmployee(EmployeeService employeeService) {
        employeeService.listEmployee();
    }
}
