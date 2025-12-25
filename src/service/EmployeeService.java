package service;


import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import domain.Employee;

public class EmployeeService {

    EmployeeDao employeeDao=new EmployeeDaoImpl();

    public void addEmployee(String name, String position, double salary){

        if(name==null || name.trim().isEmpty()){
            System.out.println("Name cannot be empty");
            return;
        }
        if(position==null || position.trim().isEmpty()){
            System.out.println("How can you be Employee without a Job Position");
            return;
        }
        if(salary<=0){
            System.out.println("Are you paying to work there bro why is you salary in negative");
            return;
        }

        Employee employee=new Employee(name,position,salary);
        employeeDao.insertEmployee(employee);
    }

    public void listEmployee(){
        employeeDao.listEmployee();
    }
}
